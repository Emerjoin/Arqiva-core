package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.context.HTMLRenderingContext;
import org.emerjoin.arqiva.core.context.MarkdownRenderingContext;
import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.context.hooks.MarkdownHook;
import org.emerjoin.arqiva.core.context.hooks.MarkupHook;

/**
 * @author Mário Júnior
 */
public class LifecycleExecutor implements MarkdownHook, MarkupHook {

    private ProjectContext projectContext = null;

    public LifecycleExecutor(ProjectContext projectContext){

        this.projectContext = projectContext;

    }

    public void beforeCompile(MarkdownRenderingContext context) {

       for(MarkdownHook markdownHook :  projectContext.getMarkdownHooks())
           markdownHook.beforeCompile(context);


    }

    public void afterCompile(MarkdownRenderingContext context) {

        for(MarkdownHook markdownHook :  projectContext.getMarkdownHooks())
            markdownHook.afterCompile(context);

    }

    public void beforeCompile(HTMLRenderingContext context) {

        for(MarkupHook markupHook  : projectContext.getMarkupHooks())
            markupHook.beforeCompile(context);


    }

    public void afterCompile(HTMLRenderingContext context) {

        for(MarkupHook markupHook  : projectContext.getMarkupHooks())
            markupHook.afterCompile(context);

    }
}
