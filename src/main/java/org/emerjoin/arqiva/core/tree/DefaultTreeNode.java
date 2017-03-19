package org.emerjoin.arqiva.core.tree;

import org.emerjoin.arqiva.core.Project;
import org.emerjoin.arqiva.core.TopicReference;

import java.io.File;
import java.util.*;

/**
 * @author Mário Júnior
 */
public class DefaultTreeNode implements TreeNode {

    private TreeNode next = null;
    private TreeNode previous = null;
    private TreeNode firstChild = null;
    private TopicReference topicReference = null;
    private String name=null;
    private String absolutePath=null;
    private Map<String,TreeNode> childs = new HashMap<String,TreeNode>();
    private List<TreeNode> childList = new ArrayList<TreeNode>();
    private TreeNode parent=null;
    private Map<String,Object> attachments = new HashMap<String, Object>();

    public DefaultTreeNode(File file, Project project){


        //Its a topic
        if(!file.isDirectory()){
            topicReference = TopicReference.get(file,project);
            if(topicReference==null)
                throw new NullPointerException(String.format("No valid TopicReference found on file %s",file.getAbsolutePath()));
            this.name = topicReference.getName();

        }else{

            if(file.getName().equals("topics"))
                name = "Topics-root";
            else {

                //Its a directory
                String nameCandidate = file.getName();
                name = nameCandidate.substring(nameCandidate.indexOf('_') + 1, nameCandidate.length());

            }
        }

    }

    public boolean isDirectory() {

        return !isTopic();

    }

    public boolean isTopic() {
        return topicReference!=null;
    }

    public String getName() {

        return name;

    }

    public String getDisplayName() {

        String name = getName();
        String capital = name.substring(0,1).toUpperCase()+name.substring(1);
        return capital;

    }

    public String getAbsolutePath() {

        return absolutePath;

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

    public boolean hasChilds() {

        return childs.size()>0;
    }

    public Collection<TreeNode> getChilds() {

        return childList;

    }

    public TreeNode getChild(String name) {

        return childs.get(name);

    }

    public boolean hasChild(String name) {

        return childs.containsKey(name);

    }

    public TopicReference getRef() {

        return topicReference;

    }

    public TreeNode getFirstChild() {

        return firstChild;

    }

    public TreeNode getLastChild() {

        if(childList.size()==0)
            return null;

        return childList.get(childList.size()-1);

    }

    public TreeNode nextTopic() {

        return DefaultTopicsTree.findNextTopicFor(this);

    }

    public TreeNode previousTopic() {

        return DefaultTopicsTree.findPreviousTopicFor(this);

    }

    public TreeNode getParent() {

        return parent;

    }

    public TreeNode deepestTopicNode() {

        TreeNode lastChild = null;
        if(isTopic())
            lastChild = this;

        return findDeepestTopicNodeIn(this);

    }

    public Object getValue(String name) {

        return attachments.get(name);

    }

    public void setValue(String name, Object value) {

        attachments.put(name,value);

    }

    public boolean hasTopicAhead() {

        return nextTopic()!=null;

    }

    public boolean hasTopicInTheLead() {

        return previousTopic()!=null;

    }

    private TreeNode findDeepestTopicNodeIn(TreeNode of){

        if(of.isDirectory() && !of.hasChilds())
            return null;

        TreeNode currentDeeper = null;

        for(TreeNode child : of.getChilds()){

            if(child.isTopic()) {
                currentDeeper = child;
                continue;
            }

            TreeNode candidate = findDeepestTopicNodeIn(child);
            if(candidate!=null)
                currentDeeper = candidate;

        }

        return currentDeeper;

    }


    public void setParent(TreeNode parent){

        this.parent = parent;

    }

    public void addChild(TreeNode node){

        if(childs.size()==0)
            firstChild = node;

        this.childs.put(node.getName(),node);
        this.childList.add(node);

    }

    public void setNext(TreeNode nextNode){

        this.next = nextNode;

    }

    public void setPrevious(TreeNode previousNode){

        this.previous = previousNode;

    }


}
