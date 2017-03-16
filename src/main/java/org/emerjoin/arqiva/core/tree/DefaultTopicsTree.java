package org.emerjoin.arqiva.core.tree;

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
        if (pathTokens.length < 2)
            throw new IllegalArgumentException("Invalid path supplied. No forward slash found");

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
}
