package org.emerjoin.arqiva.core.context;

import org.emerjoin.arqiva.core.TopicReference;

import java.util.Map;

/**
 * @author Mário Júnior
 */
public class AbstractTopicRenderingContext extends AbstractRenderingContext implements TopicRenderingContext {

    private TopicReference topicReference = null;

    public AbstractTopicRenderingContext(ProjectContext context, TopicReference topicReference){
        super(context);
        this.topicReference = topicReference;
        this.setValue("topic",topicReference);
    }

    public TopicReference getTopicReference() {

        return topicReference;

    }
}
