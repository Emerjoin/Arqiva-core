package org.emerjoin.arqiva.core.context.topic;

import org.emerjoin.arqiva.core.TopicReference;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public class TopicRenderingCtx extends AbstractTopicRenderingContext {


    public TopicRenderingCtx(ProjectContext context, TopicReference topicReference, String topicHtml) {
        super(context, topicReference, topicHtml);
    }

}
