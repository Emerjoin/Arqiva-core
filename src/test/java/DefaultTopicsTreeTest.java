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

}
