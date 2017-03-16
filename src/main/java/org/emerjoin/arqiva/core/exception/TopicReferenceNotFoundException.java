package org.emerjoin.arqiva.core.exception;

import org.emerjoin.arqiva.core.ArqivaException;

import java.io.File;

/**
 * @author Mário Júnior
 */
public class TopicReferenceNotFoundException extends ArqivaException {

    public TopicReferenceNotFoundException(File file) {
        super(String.format("No topic reference found for file %s",file.getAbsolutePath()));
    }

    public TopicReferenceNotFoundException(String url) {
        super(String.format("No topic reference found URL %s",url));
    }
}
