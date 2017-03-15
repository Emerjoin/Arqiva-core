package org.emerjoin.arqiva.core.tree;

import org.emerjoin.arqiva.core.context.ProjectContext;

import java.io.File;

/**
 * @author Mário Júnior
 */
public abstract class AbstractNode implements TreeNode {

    private boolean directory = false;
    private boolean rootNode = false;
    private boolean topic = false;
    private TreeNode next = null;
    private TreeNode previous = null;

    private String url=null;
    private String relativePath=null;
    private String absolutePath=null;

    public AbstractNode(File file, ProjectContext context){

        //TODO: Get all the details based in the two parameters above

    }

    public boolean isDirectory() {
        return directory;
    }

    public boolean isRootNode() {
        return rootNode;
    }

    public boolean isTopic() {
        return topic;
    }

    public String getAbsolutePath() {

        return absolutePath;

    }

    public String getURL() {

        return url;

    }

    public String getRelativePath() {

        return relativePath;

    }

    public TreeNode next() {

        return next;

    }

    public TreeNode previous() {

        return previous;

    }

    public boolean hasNext() {

        return next!=null;

    }

    public boolean hasPrevious() {

        return previous!=null;

    }
}
