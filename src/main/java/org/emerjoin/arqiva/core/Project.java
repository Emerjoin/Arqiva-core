package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.context.ProjectContext;
import org.emerjoin.arqiva.core.tree.TopicsTree;

/**
 * @author Mário Júnior
 */
public interface Project {

    public ProjectContext getContext();
    public TopicsTree getTopicsTree();

    /**
     * Disables the caching of generated {@link TopicsTree}.
     * Caching is active by default.
     */
    public void disableTopicsTreeCaching();

    /**
     * Enables the caching of generated {@link TopicsTree}.
     * Caching is active by default.
     */
    public void enableTopicsTreeCaching();

    /**
     * Gets a HTML template file contents.
     * @param filename the template file name without extension. Example: "topic-page" //topic-page.html
     * @return the content of the html template file if found, otherwise, null.
     */
    public String getHTMLTemplate(String filename);

}
