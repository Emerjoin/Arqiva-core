package org.emerjoin.arqiva.core;

import org.emerjoin.arqiva.core.context.*;
import org.emerjoin.arqiva.core.comp.*;

/**
 * Represents an Arqiva extension.
 * @author Mário Júnior
 */
public interface Module {


    /**
     * This method is invoked right after the creation of the {@link Module} instance.
     * Its the right place to initialize the module and add {@link ProjectBuilder}s.
     */
    public void postConstruct();

    /**
     * This method is responsible for overriding {@link BuildComponent}s on the current {@link ProjectContext}
     * @param project the current project
     */
    public void overrideComponents(Project project);


    /**
     * This is the right place to configure the {@link BuildComponent}s on the current {@link ProjectContext}
     * @param project
     */
    public void configureComponents(Project project);

}
