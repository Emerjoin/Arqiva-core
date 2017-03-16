package org.emerjoin.arqiva.core.exception;

import org.emerjoin.arqiva.core.ArqivaException;
import org.emerjoin.arqiva.core.Project;

/**
 * @author Mário Júnior
 */
public class TemplateFileNotFoundException extends ArqivaException {

    public TemplateFileNotFoundException(Project project, String templateFileName) {
        super(String.format("Template missing at %s.html",project.getContext().getSourceDirectory()+"/"+templateFileName));
    }
}
