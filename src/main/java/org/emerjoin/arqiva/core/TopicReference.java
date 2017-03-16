package org.emerjoin.arqiva.core;


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

    private static Map<String,TopicReference> referencesCache = new HashMap<String,TopicReference>();
    private static Map<String,String> topicsPathsCache = new HashMap<String, String>();

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

    public static TopicReference get(String url, Project project){

        if(topicsPathsCache.containsKey(url)){

            String absolutePath = topicsPathsCache.get(url);
            File markdownFile = new File(absolutePath);
            if(!markdownFile.exists())
                topicsPathsCache.remove(url);
            else
                return get(markdownFile,project);

        }

        String[] pathTokens = url.split("/");
        if(pathTokens.length<2) //Minimum number of tokens is 2 because topics live under the topics directory
            return null;

        String currentPath = project.getContext().getSourceDirectory()+"/topics";
        File currentFile = new File(currentPath);

        for(final String pathToken : pathTokens){

             File[] matchedFiles = currentFile.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {

                    return name.endsWith("_"+pathToken);

                }
            });

            if(matchedFiles.length!=1)
                return null;

            currentFile = matchedFiles[0];
            currentPath = currentPath+"/"+currentFile.getName();
        }

        topicsPathsCache.put(url,currentPath);
        return get(currentFile,project);

    }

    public static TopicReference get(File markdownFile, Project project){

        if(markdownFile.exists())
            throw new IllegalArgumentException(String.format("The markdown File no longer exists : %s",markdownFile.getAbsolutePath()));

        if(referencesCache.containsKey(markdownFile.getAbsolutePath()))
            return referencesCache.get(markdownFile.getAbsolutePath());

        TopicReference topicReference = new TopicReference();

        String absolutePath =  markdownFile.getAbsolutePath();
        if(!absolutePath.endsWith(".md"))//The topic files must end with .md extension
            return null;

        int sourcePathLength =  project.getContext().getSourceDirectory().length();
        String relativePath = absolutePath.substring(sourcePathLength-1,absolutePath.length());

        String[] pathTokens = relativePath.split("/");
        if(pathTokens.length<2)//Minimum number of tokens is 2 because topics live under the topics directory
            return null;


        for(String pathToken : pathTokens){

            String[] nameTokens = pathToken.split("_");
            if(nameTokens.length<2) //Every path token must follow the pattern {orderingNumber_name}
                return null;

            String orderingNumber = nameTokens[0];

            try{

                topicReference.orderingNumber = Integer.parseInt(orderingNumber);

            }catch (NumberFormatException ex){

                throw new ArqivaException(String.format("Failed to parse topic ordering number : %s",orderingNumber), ex);

            }

            String nameWithExtension = nameTokens[1];
            topicReference.name = nameWithExtension.substring(0,nameWithExtension.lastIndexOf("."));
            topicReference.filePath = absolutePath;
            topicReference.fileRelativePath = relativePath;

            referencesCache.put(absolutePath,topicReference);
            return topicReference;

        }

        return topicReference;

    }
}
