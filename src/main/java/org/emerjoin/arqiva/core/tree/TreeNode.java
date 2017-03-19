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
    public String getDisplayName();
    public TreeNode next();
    public TreeNode previous();
    public boolean hasNext();
    public boolean hasPrevious();
    public boolean hasChilds();
    public Collection<TreeNode> getChilds();
    public TreeNode getChild(String name);
    public boolean hasChild(String name);
    public TopicReference getRef();
    public TreeNode getFirstChild();
    public TreeNode getLastChild();
    public TreeNode nextTopic();
    public TreeNode previousTopic();
    public TreeNode getParent();
    public TreeNode deepestTopicNode();
    public Object getValue(String name);
    public void setValue(String name, Object value);
    public boolean hasTopicAhead();
    public boolean hasTopicInTheLead();

}
