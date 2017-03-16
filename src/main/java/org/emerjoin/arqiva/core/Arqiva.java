package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.components.MarkdownParser;
import org.emerjoin.arqiva.core.components.TemplateEngine;
import org.emerjoin.arqiva.core.context.HTMLRenderingContext;
import org.emerjoin.arqiva.core.context.MarkdownRenderingContext;
import org.emerjoin.arqiva.core.context.index.IndexPageRenderingCtx;
import org.emerjoin.arqiva.core.context.index.IndexRenderingContext;
import org.emerjoin.arqiva.core.context.topic.TopicPageRenderingCtx;
import org.emerjoin.arqiva.core.context.topic.TopicRenderingContext;
import org.emerjoin.arqiva.core.context.topic.TopicRenderingCtx;
import org.emerjoin.arqiva.core.exception.TopicReferenceNotFoundException;
import org.emerjoin.arqiva.core.jandex.JandexModulesFinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


/**
 * Represents the Arqiva public API.
 * This class is not Thread-safe class.
 * @author Mário Júnior
 */
public class Arqiva {


    private static ModulesFinder MODULES_FINDER = new JandexModulesFinder();
    public static final String PROJECT_THEME_HTML_TEMPLATE = "project-theme.html";
    public static final String INDEX_PAGE_TEMPLATE = "index-page.html";
    public static final String TOPIC_PAGE_TEMPLATE = "topic-page.html";

    public static void overrideModulesFinder(ModulesFinder modulesFinder){

        MODULES_FINDER = modulesFinder;

    }

    public static ModulesFinder getModulesFinder(){

        return MODULES_FINDER;

    }

    private Project project;
    private boolean ready = false;

    public Arqiva(Project project){

        this.project = null;

    }

    public void buildProject(String builderName){

        ArqivaProjectContext context = (ArqivaProjectContext) project.getContext();

        ProjectBuilder projectBuilder = context.getDefaultBuilder();

        if (builderName == null && projectBuilder == null)
            throw new ArqivaException(String.format("No %s set", ProjectBuilder.class.getSimpleName()));

        if (context.builderExists(builderName))
            projectBuilder = context.getBuilder(builderName);
        else
            throw new ArqivaException(String.format("Builder %s not found", builderName));

        //Call the builder
        projectBuilder.build(project);



    }


    public String renderTopic(File topicFile){
        checkReady();
        TopicReference topicReference = TopicReference.get(topicFile,project);
        if(topicReference==null)
            throw new TopicReferenceNotFoundException(topicFile);

        return renderTopic(topicReference);

    }


    public String renderTopic(String topic){
        checkReady();

        TopicReference topicReference = TopicReference.get(topic,project);
        if(topicReference==null)
            throw new TopicReferenceNotFoundException(topic);

        return renderTopic(topicReference);

    }


    private String renderHTMLPlusMarkdown(TopicRenderingContext renderingContext){

        LifecycleExecutor lifecycleExecutor = new LifecycleExecutor(project.getContext());
        lifecycleExecutor.beforeCompile((MarkdownRenderingContext) renderingContext);

        String compiledMarkdown = project.getContext().getMarkdownParser().toHTML(renderingContext.getMarkdown());
        String compiledMarkdownPlusHTMLTemplate = merge(compiledMarkdown,renderingContext.getHtml());
        renderingContext.updateHtml(compiledMarkdownPlusHTMLTemplate);

        lifecycleExecutor.afterCompile((MarkdownRenderingContext) renderingContext);
        lifecycleExecutor.beforeCompile((HTMLRenderingContext) renderingContext);
        project.getContext().getTemplateEngine().run(renderingContext);
        lifecycleExecutor.afterCompile((HTMLRenderingContext) renderingContext);

        return renderingContext.getHtml();

    }

    private String renderTopic(TopicReference topicReference){

        String topicMarkdown = topicReference.getMarkdownContent();
        String topicTemplateHtml = project.getHTMLTemplate(TOPIC_PAGE_TEMPLATE);
        TopicRenderingContext renderingContext = new TopicRenderingCtx(project.getContext(),topicReference,
                topicTemplateHtml,topicMarkdown);

        return renderHTMLPlusMarkdown(renderingContext);

    }

    private String renderTopicPage(TopicReference topicReference){

        String topicMarkdown = topicReference.getMarkdownContent();
        String topicTemplateHtml = project.getHTMLTemplate(TOPIC_PAGE_TEMPLATE);
        String themeTemplateHtml = project.getHTMLTemplate(PROJECT_THEME_HTML_TEMPLATE);

        TopicRenderingContext renderingContext = new TopicRenderingCtx(project.getContext(),topicReference,
                merge(topicTemplateHtml,themeTemplateHtml),topicMarkdown);

        return renderHTMLPlusMarkdown(renderingContext);

    }


    public String renderTopicPage(File topicFile){
        checkReady();

        TopicReference topicReference = TopicReference.get(topicFile,project);
        if(topicReference==null)
            throw new TopicReferenceNotFoundException(topicFile);

        return renderTopicPage(topicReference);

    }


    public String renderTopicPage(String topic){
        checkReady();

        TopicReference topicReference = TopicReference.get(topic,project);
        if(topicReference==null)
            throw new TopicReferenceNotFoundException(topic);

        return renderTopicPage(topicReference);

    }


    private String renderHtml(HTMLRenderingContext renderingContext){

        LifecycleExecutor lifecycleExecutor = new LifecycleExecutor(project.getContext());
        lifecycleExecutor.beforeCompile(renderingContext);
        project.getContext().getTemplateEngine().run(renderingContext);
        lifecycleExecutor.afterCompile(renderingContext);
        return renderingContext.getHtml();

    }

    public String renderIndex(){

        checkReady();
        String indexHtml = project.getHTMLTemplate(INDEX_PAGE_TEMPLATE);
        IndexRenderingContext renderingContext = new IndexPageRenderingCtx(project.getContext(),indexHtml);
        return renderHtml(renderingContext);

    }

    public String renderIndexPage(){

        checkReady();
        String indexHtml = project.getHTMLTemplate(INDEX_PAGE_TEMPLATE);
        String themeHtml = project.getHTMLTemplate(PROJECT_THEME_HTML_TEMPLATE);
        IndexPageRenderingCtx renderingCtx = new IndexPageRenderingCtx(project.getContext(),merge(indexHtml,themeHtml));
        return renderHtml(renderingCtx);

    }

    private void checkReady(){
        if(!ready)
            getReady();

    }

    private void getReady(){

        //Initialize modules (modules may override and configure build components)
        initModules();

        //Start build components
        if(project.getContext().getMarkdownParser()==null)
            throw new ArqivaException(String.format("No %s component instance set", MarkdownParser.class.getSimpleName()));
        project.getContext().getMarkdownParser().startComponent();


        if(project.getContext().getTemplateEngine()==null)
            throw new ArqivaException(String.format("No %s component instance set", TemplateEngine.class.getSimpleName()));
        project.getContext().getTemplateEngine().startComponent();


    }



    private void initModules(){

        final List<Module> modulesList = new ArrayList<Module>();

        //Load modules and ask them to override the build components
        MODULES_FINDER.findModules(new Function<String, Void>() {

            public Void apply(String clazz) {

                ModuleInfo moduleInfo = new ModuleInfo(clazz);
                Module module =  moduleInfo.construct();
                module.overrideComponents(Arqiva.this.project);
                modulesList.add(module);
                return null;
            }

        });

        //Ask modules to configure build components
        for(Module module : modulesList)
            module.configureComponents(this.project);

    }

    private String merge(String content, String container){

        return container.replace("<!--@content-->",container);

    }

}
