package org.emerjoin.arqiva.core.tree;

/**
 * @author Mário Júnior
 */
public interface TreeNode {

    public boolean isDirectory();
    public boolean isRootNode();
    public boolean isTopic();
    public String getAbsolutePath();
    public String getURL();
    public String getRelativePath();
    public TreeNode next();
    public TreeNode previous();
    public boolean hasNext();
    public boolean hasPrevious();

}
