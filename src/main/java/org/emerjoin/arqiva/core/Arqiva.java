package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.components.MarkdownParser;
import org.emerjoin.arqiva.core.components.TemplateEngine;
import org.emerjoin.arqiva.core.jandex.JandexModulesFinder;

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


    public String renderTopic(String topic){
        checkReady();

        //TODO:
        throw new MustBeImplementedException();

    }


    public String renderTopicPage(String topic){
        checkReady();

        //TODO:
        throw new MustBeImplementedException();

    }

    private String renderTheme(){
        checkReady();

        //TODO: Load index-template HTML file + theme HTML file
        //TODO: Create an IndexPageRenderingContext instance
        //TODO: Execute the lifecycle: beforeCompile
        //TODO: Compile using the template engine
        //TODO: Execute the lifecyle: beforeOutput
        //TODO: Return the final HTML

        //TODO:
        throw new MustBeImplementedException();

    }


    public String renderIndex(){
        checkReady();

        //TODO: Load index-template HTML file
        //TODO: Create an IndexRenderingContext instance
        //TODO: Execute the lifecycle: beforeCompile
        //TODO: Compile using the template engine
        //TODO: Execute the lifecyle: beforeOutput
        //TODO: Return the final HTML

        throw new MustBeImplementedException();

    }

    public String renderIndexPage(){
        checkReady();



        throw new MustBeImplementedException();

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

}
