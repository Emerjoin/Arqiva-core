package org.emerjoin.arqiva.core;


import org.emerjoin.arqiva.core.tree.TopicsTree;
import org.emerjoin.arqiva.core.tree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mário Júnior
 */
public class TopicReference {

    private String filePath;
    private String fileRelativePath;
    private String url;
    private String name;
    private int orderingNumber;
    private Project project = null;

    private static Map<String,TopicReference> referencesCache = new HashMap<String,TopicReference>();
    private static Map<String,String> topicsPathsCache = new HashMap<String, String>();
    private static Logger log = LoggerFactory.getLogger(TopicReference.class);

    private TopicReference(){}

    public String getFilePath() {
        return filePath;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }


    public int getOrderingNumber() {
        return orderingNumber;
    }

    public String getFileRelativePath() {
        return fileRelativePath;
    }

    public String getMarkdownContent(){

        return FileUtils.getFileContents(new File(filePath));

    }

    public TreeNode getTreeNode(){

        TopicsTree treeNode = project.getTopicsTree().subTree(url);
        if(treeNode==null)
            return null;

        return treeNode.getRootNode();

    }

    public static TopicReference get(String url, Project project){



        if(topicsPathsCache.containsKey(url)){

            String absolutePath = topicsPathsCache.get(url);
            File markdownFile = new File(absolutePath);
            if(!markdownFile.exists())
                topicsPathsCache.remove(url);
            else
                return get(markdownFile,project);

        }

        String[] pathTokens = null;
        if(url.indexOf(String.valueOf(File.separator))==-1)
            pathTokens = new String[]{url};
        else pathTokens = url.split("/");

        String currentPath = project.getContext().getSourceDirectory()+File.separator+"topics";
        File currentFile = new File(currentPath);

        int currentIndex = 0;
        for(final String pathToken : pathTokens){

            final boolean isLastToken = currentIndex == pathTokens.length-1;

             File[] matchedFiles = currentFile.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {

                    String endToken = "_"+pathToken+((isLastToken)?".md":"");
                    return name.endsWith(endToken);

                }
            });

            if(matchedFiles.length!=1)
                return null;

            currentFile = matchedFiles[0];
            currentPath = currentPath+File.separator+currentFile.getName();
            currentIndex++;
        }

        topicsPathsCache.put(url,currentPath);
        return get(currentFile,project);

    }

    public static TopicReference get(File markdownFile, Project project){

        if(!markdownFile.exists())
            throw new IllegalArgumentException(String.format("The markdown File no longer exists : %s",markdownFile.getAbsolutePath()));

        if(referencesCache.containsKey(markdownFile.getAbsolutePath()))
            return referencesCache.get(markdownFile.getAbsolutePath());

        TopicReference topicReference = new TopicReference();
        topicReference.project = project;

        String absolutePath =  markdownFile.getAbsolutePath();
        if(!absolutePath.endsWith(".md"))//The topic files must end with .md extension
            return null;

        int sourcePathLength =  (project.getContext().getSourceDirectory()+File.separator+"topics").length();
        String relativePath = absolutePath.substring(sourcePathLength+1,absolutePath.length());

        String[] pathTokens = relativePath.split(String.valueOf(File.separator));

        String urlBuilder = "";
        byte tokenIndex = 0;
        int lastTokenIndex = pathTokens.length-1;
        for(String pathToken : pathTokens){

            String[] nameTokens = pathToken.split("_");
            if(nameTokens.length<2) //Every path token must follow the pattern {orderingNumber_name}
                return null;

            String orderingNumber = nameTokens[0];
            String nameWithExtension = nameTokens[1];

            //Its the last token and no sign of markdown filename
            if(!nameWithExtension.endsWith(".md")&&tokenIndex==lastTokenIndex)
                return null;
            else if(!nameWithExtension.endsWith(".md")&&tokenIndex<lastTokenIndex){
                //Middle path
                if(!urlBuilder.equals(""))
                    urlBuilder = urlBuilder+"/";
                urlBuilder = urlBuilder+nameWithExtension;
                tokenIndex++;
                continue;
            }

            //Its the token with the markdown filename
            String nameWithoutExtension = nameWithExtension.substring(0,nameWithExtension.lastIndexOf("."));
            try{

                topicReference.url = (urlBuilder.equals(""))?nameWithoutExtension: urlBuilder+"/"+nameWithoutExtension;
                topicReference.orderingNumber = Integer.parseInt(orderingNumber);

            }catch (NumberFormatException ex){

                throw new ArqivaException(String.format("Failed to parse topic ordering number : %s",orderingNumber), ex);

            }

            //topicReference.name = nameWithoutExtension.substring(0,1).toUpperCase()+nameWithoutExtension.substring(1);
            topicReference.name = nameWithoutExtension;
            topicReference.filePath = absolutePath;
            topicReference.fileRelativePath = relativePath;
            referencesCache.put(absolutePath,topicReference);
            return topicReference;

        }

        return null;

    }

    public String getHtmlUrl(){

        return getUrl()+".html";

    }
}
