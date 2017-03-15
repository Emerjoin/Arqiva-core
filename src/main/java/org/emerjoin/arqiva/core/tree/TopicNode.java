package org.emerjoin.arqiva.core.tree;

import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.TopicReference;

import java.io.File;

/**
 * @author Mário Júnior
 */
public class TopicNode extends AbstractNode {

    private TopicReference topicReference = null;

    public TopicNode(File file, ProjectContext context) {
        super(file, context);

        //TODO: Get the topic reference and set it

    }

    public TopicReference getTopicReference(){

        return topicReference;

    }

}
