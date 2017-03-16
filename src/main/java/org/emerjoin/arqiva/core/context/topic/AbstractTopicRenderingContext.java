package org.emerjoin.arqiva.core.context.topic;

import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.AbstractHTMLRenderingContext;
import org.emerjoin.arqiva.core.context.AbstractRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public abstract class AbstractTopicRenderingContext extends AbstractHTMLRenderingContext implements TopicRenderingContext {

    private TopicReference topicReference = null;
    private String markdown = null;
    private String compiledMarkdown;

    public AbstractTopicRenderingContext(ProjectContext context, TopicReference topicReference, String html, String markdown){
        super(context,html);
        this.topicReference = topicReference;
        this.markdown = markdown;
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

    public void setCompiledMarkdown(String html){

        this.compiledMarkdown = html;

    }

    public String getCompiledMarkdown() {

        return compiledMarkdown;

    }
}
