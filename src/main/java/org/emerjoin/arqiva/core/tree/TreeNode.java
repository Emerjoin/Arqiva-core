package org.emerjoin.arqiva.core.tree;

import org.emerjoin.arqiva.core.TopicReference;

import java.util.Collection;

/**
 * @author Mário Júnior
 */
public interface TreeNode {

    public boolean isDirectory();
    public boolean isTopic();
    public String getName();
    public TreeNode next();
    public TreeNode previous();
    public boolean hasNext();
    public boolean hasPrevious();
    public boolean hasChilds();
    public Collection<TreeNode> getChilds();
    public TreeNode getChild(String name);
    public boolean hasChild(String name);
    public TopicReference getTopicReference();

}
