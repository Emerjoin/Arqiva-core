package org.emerjoin.arqiva.core.comp;

import org.emerjoin.arqiva.core.context.HTMLRenderingContext;

/**
 * @author Mário Júnior
 */
public interface TemplateEngine extends BuildComponent {

    public void run(HTMLRenderingContext context);

}
