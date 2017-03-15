package org.emerjoin.arqiva.core.context.hooks;

import org.emerjoin.arqiva.core.context.MarkdownRenderingContext;
import org.emerjoin.arqiva.core.context.RenderingContext;

/**
 * @author Mário Júnior
 */
public interface MarkdownHook extends Hook {

    /**
     * This method gets invoked before the markdown is compiled to HTML.
     * It's the right to transform the markdown before its compiled.
     * @param context the current {@link MarkdownRenderingContext}
     */
    public void beforeCompile(MarkdownRenderingContext context);

    /**
     * This method gets invoked right after the markdown get compiled to HTML.
     * It's the right place to transform the HTML generated from the markdown file.
     * @param context the current {@link MarkdownRenderingContext}
     */
    public void afterCompile(MarkdownRenderingContext context);

}
