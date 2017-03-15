package org.emerjoin.arqiva.core.context.index;

import org.emerjoin.arqiva.core.context.AbstractHTMLRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;

/**
 * @author Mário Júnior
 */
public class IndexRenderingCtx extends AbstractHTMLRenderingContext implements IndexRenderingContext {

    public IndexRenderingCtx(ProjectContext root, String html) {
        super(root, html);
    }
}
