package org.emerjoin.arqiva.core.context.topic;

import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.FullPageRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public class TopicPageRenderingCtx extends AbstractTopicRenderingContext implements FullPageRenderingContext {

    public TopicPageRenderingCtx(ProjectContext context, TopicReference topicReference, String pageHtml, String markdown) {
        super(context, topicReference,pageHtml,markdown);
    }

}
