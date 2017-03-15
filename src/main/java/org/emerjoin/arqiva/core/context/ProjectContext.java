package org.emerjoin.arqiva.core.context;

import org.emerjoin.arqiva.core.ProjectBuilder;
import org.emerjoin.arqiva.core.comp.MarkdownParser;
import org.emerjoin.arqiva.core.comp.TemplateEngine;
import org.emerjoin.arqiva.core.context.hooks.Hook;

/**
 * @author Mário Júnior
 */
public interface ProjectContext extends ContextValues {

    public String getSourceDirectory();
    public String getTargetDirectory();
    public MarkdownParser getMarkdownParser();
    public TemplateEngine getTemplateEngine();
    public void addBuilder(ProjectBuilder builder);
    public void addHook(Hook hook);

}
