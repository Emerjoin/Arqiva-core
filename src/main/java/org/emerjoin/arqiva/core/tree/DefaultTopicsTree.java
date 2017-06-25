package org.emerjoin.arqiva.core.tree;

import java.io.File;

/**
 * @author Mário Júnior
 */
public class DefaultTopicsTree implements TopicsTree {

    private TreeNode treeNode = null;

    public DefaultTopicsTree(TreeNode treeNode){

        this.treeNode = treeNode;

    }

    public TopicsTree subTree(String path) {

        String[] pathTokens = path.split("/");
        if(pathTokens.length==0)
            return new DefaultTopicsTree(treeNode);

        int nodeToFindIndex = 0;
        String currentPath = "";
        TreeNode currentNode = treeNode;
        boolean firstRound = true;

        while (!(currentPath.equals(path)) && currentNode.hasChilds()) {

            String nodeToFindName = pathTokens[nodeToFindIndex];

            if (currentNode.hasChild(nodeToFindName)) {
                currentNode = currentNode.getChild(nodeToFindName);
                if (!firstRound)
                    currentPath = currentPath + "/";
                else firstRound = false;
                currentPath = currentPath + nodeToFindName;
            } else break;

            nodeToFindIndex++;

        }

        if (!currentPath.equals(path))
            return null;
        return new DefaultTopicsTree(currentNode);
    }

    public TreeNode getRootNode() {

        return treeNode;

    }

    public TreeNode firstTopic() {

        TreeNode node = treeNode.getFirstChild();
        if(node.isTopic())
            return node;

        return findFirstTopicIn(node);

    }

    public static TreeNode findFirstTopicIn(TreeNode treeNode){

        for(TreeNode child : treeNode.getChilds()){

            if(child.isTopic())
                return child;

            TreeNode found = findFirstTopicIn(child);
            if(found!=null)
                return found;


        }

        return null;

    }

    public static TreeNode findNextTopicFor(TreeNode node){

        if(node==null)
            return null;

        if(!(node.hasNext())&&node.getParent()==null)
            return null;

        TreeNode currentNode = node.next();

        if(currentNode==null) {

            TreeNode parentNext = node.getParent().next();

            if(parentNext==null)
                return findNextTopicFor(node.getParent().getParent());

            //The bother of the parent is a topic
            if (parentNext.isTopic())
                return parentNext;

            //The brother of the parent is not a topic but has no first child
            if (parentNext.getFirstChild() == null)
                return findNextTopicFor(parentNext);

            //The first child of the parent's brother is a topic
            currentNode = parentNext.getFirstChild();

        }

        if(currentNode.isTopic())
            return currentNode;

        TreeNode candidate = findFirstTopicIn(currentNode);
        if(candidate.isTopic())
            return candidate;

        while(currentNode.hasNext()){
            currentNode = currentNode.next();
            if(currentNode.isTopic())
                return currentNode;
            return findNextTopicFor(currentNode);
        }

        return null;

    }


    public static TreeNode findPreviousTopicFor(TreeNode node){

        if(node==null)
            return null;

        if(!(node.hasPrevious())&&node.getParent()==null)
            return null;

        TreeNode currentNode = node.previous();

        if(currentNode==null) {

            TreeNode parentPrevious = node.getParent().previous();
            if(parentPrevious==null)
                return findPreviousTopicFor(node.getParent().getParent());

            //The bother of the parent is a topic
            if (parentPrevious.isTopic())
                return findPreviousTopicFor(parentPrevious);


            return parentPrevious.deepestTopicNode();

        }

        if(currentNode.isTopic())
            return currentNode;

        return currentNode.deepestTopicNode();


    }
}
