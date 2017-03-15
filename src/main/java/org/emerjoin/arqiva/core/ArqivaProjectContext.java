package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.components.MarkdownParser;
import org.emerjoin.arqiva.core.components.TemplateEngine;
import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.context.hooks.Hook;
import org.emerjoin.arqiva.core.context.hooks.MarkdownHook;
import org.emerjoin.arqiva.core.context.hooks.MarkupHook;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mário Júnior
 */
public class ArqivaProjectContext implements ProjectContext {

    private String src;
    private String target;
    private MarkdownParser markdownParser;
    private TemplateEngine templateEngine;
    private Map<String,Object> values = new HashMap<String, Object>();
    private Map<String,ProjectBuilder> builders = new HashMap<String, ProjectBuilder>();
    private List<MarkdownHook> markdownHooks = new ArrayList<MarkdownHook>();
    private List<MarkupHook> markupHooks = new ArrayList<MarkupHook>();

    public ArqivaProjectContext(String src, String target){

        this.src = src;
        this.target = target;

    }

    public void setMarkdownParser(MarkdownParser parser){

        this.markdownParser = parser;

    }

    public void setTemplateEngine(TemplateEngine templateEngine){

        this.templateEngine = templateEngine;

    }

    public String getSourceDirectory() {

        return src;

    }

    public String getTargetDirectory() {

        return  target;

    }

    public MarkdownParser getMarkdownParser() {

        return markdownParser;

    }

    public TemplateEngine getTemplateEngine() {

        return templateEngine;

    }

    public void addBuilder(ProjectBuilder builder) {
        if(builder==null)
            throw new NullPointerException("cant add a null builder");

        Annotation declaredAnnotation = builder.getClass().getDeclaredAnnotation(Named.class);
        if(declaredAnnotation==null)
            throw new ArqivaException(String.format("%s annotation is missing on builder class %s",
                    Named.class.getSimpleName(),builder.getClass().getCanonicalName()));

        String builderName = Common.getValidName((Named) declaredAnnotation,builder.getClass());
        this.builders.put(builderName,builder);

    }

    public void addHook(Hook hook) {

        if(hook instanceof MarkdownHook)
            markdownHooks.add((MarkdownHook) hook);
        else if(hook instanceof MarkupHook)
            markupHooks.add((MarkupHook) hook);

    }

    public void setValue(String name, Object value) {

        this.values.put(name,value);

    }

    public boolean hasValue(String name) {

        return values.containsKey(name);
    }

    public Object getValue(String name) {

        return values.get(name);

    }

    public Map<String, Object> getValues() {
        return values;
    }


    protected boolean builderExists(String name){

        return builders.containsKey(name);

    }

    protected ProjectBuilder getBuilder(String name){

        return builders.get(name);

    }


    /**
     * Gets the first or only builder added to the current {@link ArqivaProjectContext}.
     * @return null if no builder was added, otherwise a {@link ProjectBuilder} instance is returned.
     */
    protected ProjectBuilder getDefaultBuilder(){

        if(builders.size()<1)
            return null;

        ProjectBuilder[] buildersArray = new ProjectBuilder[builders.size()];
        builders.values().toArray(buildersArray);

        return buildersArray[0];

    }

    protected List<MarkupHook> getMarkupHooks(){

        return markupHooks;

    }

    protected List<MarkdownHook> getMarkdownHooks(){

        return markdownHooks;

    }
}
