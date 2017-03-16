/**
 * @author Mário Júnior
 */
import org.emerjoin.arqiva.core.ArqivaProject;
import org.emerjoin.arqiva.core.ArqivaProjectContext;
import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.tree.TopicsTree;
import org.emerjoin.arqiva.core.tree.TreeNode;
import org.junit.Test;

import java.io.File;

import  static org.junit.Assert.*;

public class ArqivaProjectTest {


    @Test
    public void generatedTopicsTreeMustMatchTheTopicsDirectory(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        Object treeObject = context.getValue("tree");
        assertNotNull(treeObject);

        TopicsTree tree = (TopicsTree)  treeObject;
        TreeNode rootNode = tree.getRootNode();
        assertNotNull(rootNode);
        assertFalse(rootNode.isTopic());
        assertTrue(rootNode.isDirectory());

        TreeNode helloWorldNODE = rootNode.getFirstChild().getFirstChild().getFirstChild();
        assertNotNull(helloWorldNODE);
        assertEquals("Hello-world",helloWorldNODE.getName());
        assertTrue(helloWorldNODE.isTopic());
        assertFalse(helloWorldNODE.isDirectory());

        TreeNode coolTopic2NODE = rootNode.getFirstChild().next().getFirstChild().next().getFirstChild().next();
        assertNotNull(coolTopic2NODE);
        assertEquals("Topic-cool-2",coolTopic2NODE.getName());
        assertTrue(coolTopic2NODE.isTopic());
        assertFalse(coolTopic2NODE.isDirectory());


    }



}
