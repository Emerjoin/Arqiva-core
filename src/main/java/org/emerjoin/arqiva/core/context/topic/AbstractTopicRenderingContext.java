package org.emerjoin.arqiva.core.context.topic;

import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.AbstractHTMLRenderingContext;
import org.emerjoin.arqiva.core.context.AbstractRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public class AbstractTopicRenderingContext extends AbstractHTMLRenderingContext implements TopicRenderingContext {

    private TopicReference topicReference = null;
    private String markdown = null;

    public AbstractTopicRenderingContext(ProjectContext context, TopicReference topicReference, String html){
        super(context,html);
        this.topicReference = topicReference;
        this.setValue("topic",topicReference);
    }

    public TopicReference getTopicReference() {

        return topicReference;

    }

    public void updateMarkdown(String markdown) {

        this.markdown = markdown;

    }

    public String getMarkdown() {

        return markdown;

    }
}
