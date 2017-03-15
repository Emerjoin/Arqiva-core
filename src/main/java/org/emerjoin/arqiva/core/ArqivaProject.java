package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.tree.TopicsTree;

/**
 * @author Mário Júnior
 */
public class ArqivaProject implements Project {

    private ProjectContext context = null;
    private TopicsTree topicsTree = null;
    private boolean cacheTopicsTree = true;


    public ArqivaProject(ProjectContext context){

        this.context = context;

    }

    public ProjectContext getContext() {

        return context;

    }

    public TopicsTree getTopicsTree() {

        //TODO: Build and cache the topics tree
        return topicsTree;

    }

    public void disableTopicsTreeCaching() {

        cacheTopicsTree = false;

    }

    public void enableTopicsTreeCaching() {

        cacheTopicsTree = true;

    }

    public String getHTMLTemplate(String filename) {

        //TODO: Implement
        throw new MustBeImplementedException();

    }

}
