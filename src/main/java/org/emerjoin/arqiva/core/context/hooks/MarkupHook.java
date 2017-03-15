package org.emerjoin.arqiva.core.context.hooks;

import org.emerjoin.arqiva.core.context.HTMLRenderingContext;
import org.emerjoin.arqiva.core.context.RenderingContext;
import org.emerjoin.arqiva.core.comp.*;

/**
 * @author Mário Júnior
 */
public interface MarkupHook extends Hook {

    /**
     * This method gets invoked before an HTML file get compiled by the {@link TemplateEngine}
     * @param context
     */
    public void beforeCompile(HTMLRenderingContext context);


    /**
     * This method gets invoked right after the HTML is compiled by the {@link TemplateEngine}
     * @param context
     */
    public void beforeOutput(HTMLRenderingContext context);

}
