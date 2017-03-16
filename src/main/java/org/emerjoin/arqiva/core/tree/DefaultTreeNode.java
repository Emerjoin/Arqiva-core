package org.emerjoin.arqiva.core.tree;

import org.emerjoin.arqiva.core.Project;
import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.ProjectContext;

import java.io.File;
import java.util.*;

/**
 * @author Mário Júnior
 */
public class DefaultTreeNode implements TreeNode {

    private boolean directory = false;
    private TreeNode next = null;
    private TreeNode previous = null;
    private TreeNode firstChild = null;
    private TopicReference topicReference = null;
    private String name=null;
    private String absolutePath=null;
    private Map<String,TreeNode> childs = new HashMap<String,TreeNode>();

    public DefaultTreeNode(File file, Project project){


        //Its a topic
        if(!file.isDirectory()){
            topicReference = TopicReference.get(file,project);
            if(topicReference==null)
                throw new NullPointerException(String.format("No valid TopicReference found on file %s",file.getAbsolutePath()));
            this.name = topicReference.getName();

        }else{

            if(file.getName().equals("topics"))
                name = "topics-root";
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

        return childs.values();

    }

    public TreeNode getChild(String name) {

        return childs.get(name);

    }

    public boolean hasChild(String name) {

        return childs.containsKey(name);

    }

    public TopicReference getTopicReference() {

        return topicReference;

    }

    public TreeNode getFirstChild() {

        return firstChild;

    }

    public void addChild(TreeNode node){

        if(childs.size()==0)
            firstChild = node;

        this.childs.put(node.getName(),node);

    }

    public void setNext(TreeNode nextNode){

        this.next = nextNode;

    }

    public void setPrevious(TreeNode previousNode){

        this.previous = previousNode;

    }


}
