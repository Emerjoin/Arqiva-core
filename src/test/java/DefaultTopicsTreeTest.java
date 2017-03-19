import org.emerjoin.arqiva.core.ArqivaProject;
import org.emerjoin.arqiva.core.ArqivaProjectContext;
import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.tree.TopicsTree;
import org.emerjoin.arqiva.core.tree.TreeNode;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @author Mário Júnior
 */
public class DefaultTopicsTreeTest {


    @Test
    public void hasTopicAheadMustBeTrue(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("getting-started/first-program/Hello-world");
        assertNotNull(subTree);

        TreeNode rootNode = subTree.getRootNode();
        assertNotNull(rootNode);
        assertTrue(rootNode.hasTopicAhead());
    }


    @Test
    public void hasTopicAheadMustBeFalse(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2/Topic-cool-2");
        assertNotNull(subTree);

        TreeNode rootNode = subTree.getRootNode();
        assertNotNull(rootNode);
        assertFalse(rootNode.hasTopicAhead());
    }


    @Test
    public void hasTopicInTheLeadMustBeFalse(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("getting-started/first-program/Hello-world");
        assertNotNull(subTree);

        TreeNode rootNode = subTree.getRootNode();
        assertNotNull(rootNode);
        assertFalse(rootNode.hasTopicInTheLead());

    }


    @Test
    public void hasTopicInTheLeadMustBeTrue(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2/Topic-cool-1");
        assertNotNull(subTree);

        TreeNode rootNode = subTree.getRootNode();
        assertNotNull(rootNode);
        assertTrue(rootNode.hasTopicInTheLead());

    }

    @Test
    public void hasTopicInTheLeadMustBeAlsoTrue(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2/Topic-cool-2");
        assertNotNull(subTree);
        TreeNode rootNode = subTree.getRootNode();
        assertNotNull(rootNode);
        assertTrue(rootNode.hasTopicInTheLead());

    }


    @Test
    public void subTreeMustReturnTreeWithRootNodeWithTwoChilds(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2");
        assertNotNull(subTree);

        assertEquals(2,subTree.getRootNode().getChilds().size());
        TreeNode coolTopic1NODE = subTree.getRootNode().getFirstChild();
        assertEquals("Topic-cool-1",coolTopic1NODE.getName());

    }


    @Test
    public void previousTopicMustReturnTopicB(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2");
        assertNotNull(subTree);

        assertEquals(2,subTree.getRootNode().getChilds().size());
        TreeNode coolTopic1NODE = subTree.getRootNode().getFirstChild();
        TreeNode previousTopic = coolTopic1NODE.previousTopic();
        assertNotNull(previousTopic);
        assertEquals("Topic-B",previousTopic.getName());

    }



    @Test
    public void nextTopicMustBeTopicCool1(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TopicsTree subTree = tree.subTree("reference/level-2");
        assertNotNull(subTree);

        assertEquals(2,subTree.getRootNode().getChilds().size());
        TreeNode coolTopic1NODE = subTree.getRootNode().getFirstChild();
        TreeNode previousTopic = coolTopic1NODE.previousTopic();
        assertNotNull(previousTopic);
        assertEquals("Topic-B",previousTopic.getName());

        TreeNode nextTopic = previousTopic.nextTopic();
        assertNotNull(nextTopic);
        assertEquals("Topic-cool-1",nextTopic.getName());

    }


    @Test
    public void firstTopicMustBeHelloWorld(){

        String projectsDirectory = new File("").getAbsolutePath()+"/test-projects/test-1";
        ProjectContext context = new ArqivaProjectContext(projectsDirectory,"");
        new ArqivaProject(context);
        TopicsTree tree = (TopicsTree)  context.getValue("tree");

        TreeNode firstTopic = tree.firstTopic();
        assertNotNull(firstTopic);
        assertEquals("Hello-world",firstTopic.getName());

    }

}
