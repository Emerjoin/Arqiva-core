package org.emerjoin.arqiva.core.context.index;

import org.emerjoin.arqiva.core.context.AbstractHTMLRenderingContext;
import org.emerjoin.arqiva.core.context.FullPageRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public class IndexPageRenderingCtx extends AbstractHTMLRenderingContext implements IndexRenderingContext, FullPageRenderingContext {

    public IndexPageRenderingCtx(ProjectContext root, String html) {
        super(root, html);
    }

}
