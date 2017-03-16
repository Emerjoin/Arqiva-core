package org.emerjoin.arqiva.core.tree;

/**
 * @author Mário Júnior
 */
public interface TopicsTree {

    /**
     * Creates a sub-tree of the tree from a specific node level
     * @param path the node level/path from which the sub-tree must be created. A valid path has a minimum of one forward slash. Example: getting-started/examples
     * @return the sub {@link TopicsTree} if the node path can be matched, otherwise null is returned.
     */
    public TopicsTree subTree(String path);

    /**
     * Gets the root {@link TreeNode} of the current {@link TopicsTree}.
     * @return the {@link TreeNode} instance that represents the current of the currrent {@link TopicsTree}.
     */
    public TreeNode getRootNode();

}
