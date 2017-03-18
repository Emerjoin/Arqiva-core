package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.exception.TemplateFileNotFoundException;
import org.emerjoin.arqiva.core.tree.DefaultTopicsTree;
import org.emerjoin.arqiva.core.tree.DefaultTreeNode;
import org.emerjoin.arqiva.core.tree.TopicsTree;
import org.emerjoin.arqiva.core.tree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author Mário Júnior
 */
public class ArqivaProject implements Project {

    private ProjectContext context = null;
    private TopicsTree topicsTree = null;

    private static final Logger log = LoggerFactory.getLogger(ArqivaProject.class);


    public ArqivaProject(ProjectContext context){

        this.context = context;
        context.setValue("tree", new TopicsTree() {

            public TopicsTree subTree(String path) {

                return getCachedTopicsTree().subTree(path);

            }

            public TreeNode getRootNode() {

                return getCachedTopicsTree().getRootNode();

            }
        });

    }

    public ProjectContext getContext() {

        return context;

    }

    public void invalidateTopicsTree(){

        this.topicsTree = null;

    }

    private TopicsTree getCachedTopicsTree() {

        if(topicsTree==null)
            topicsTree = buildTopicsTree();

      return topicsTree;

    }

    private TopicsTree buildTopicsTree(){

       File topisDirectory = new File(context.getSourceDirectory()+"/topics");
       TreeNode rootNode = getTreeNode(topisDirectory);
       return new DefaultTopicsTree(rootNode);

    }

    private DefaultTreeNode getTreeNode(File directory){

        DefaultTreeNode treeNode = new DefaultTreeNode(directory,this);

        File[] files = directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {

                return !(name.equals(".")||name.equals(".."));

            }
        });

        DefaultTreeNode previousNode = null;
        for(File file: files){

            if(file.isFile()&&!file.getName().endsWith(".md"))//Only topic files
                continue;

            DefaultTreeNode currentTreeNode = null;
            if(file.isDirectory())
                currentTreeNode = getTreeNode(file);
            else
                currentTreeNode = new DefaultTreeNode(file,this);

            treeNode.addChild(currentTreeNode);
            if(previousNode==null){
                previousNode = currentTreeNode;
                continue;
            }

            previousNode.setNext(currentTreeNode);
            currentTreeNode.setPrevious(previousNode);


        }

        return treeNode;

    }

    public String getHTMLTemplate(String filename) throws TemplateFileNotFoundException {

        File targetFile = new File(context.getSourceDirectory()+"/"+filename+".html");
        if(targetFile.exists())
            return FileUtils.getFileContents(targetFile);
        else
            log.warn(String.format("HTML file %s was not found",targetFile.getAbsolutePath()));

        return "";

    }

}
