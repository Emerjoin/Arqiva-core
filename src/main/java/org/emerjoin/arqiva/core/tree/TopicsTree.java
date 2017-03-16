package org.emerjoin.arqiva.core.tree;

/**
 * @author Mário Júnior
 */
public interface TopicsTree {

    public TopicsTree subTree(String fromNode);
    public TreeNode getRootNode();

}
