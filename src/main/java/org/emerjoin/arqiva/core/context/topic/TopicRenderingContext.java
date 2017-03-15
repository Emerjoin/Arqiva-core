package org.emerjoin.arqiva.core.context.topic;

import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.HTMLRenderingContext;
import org.emerjoin.arqiva.core.context.MarkdownRenderingContext;
import org.emerjoin.arqiva.core.context.RenderingContext;

/**
 * @author Mário Júnior
 */
public interface TopicRenderingContext extends MarkdownRenderingContext {

    public TopicReference getTopicReference();

}
