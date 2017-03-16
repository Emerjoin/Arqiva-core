package org.emerjoin.arqiva.core.context;

import org.emerjoin.arqiva.core.ProjectBuilder;
import org.emerjoin.arqiva.core.components.MarkdownParser;
import org.emerjoin.arqiva.core.components.TemplateEngine;
import org.emerjoin.arqiva.core.context.hooks.Hook;
import org.emerjoin.arqiva.core.context.hooks.MarkdownHook;
import org.emerjoin.arqiva.core.context.hooks.MarkupHook;

import java.util.List;

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
    public boolean builderExists(String name);
    public ProjectBuilder getBuilder(String name);
    public ProjectBuilder getDefaultBuilder();
    public List<MarkupHook> getMarkupHooks();
    public List<MarkdownHook> getMarkdownHooks();

}
