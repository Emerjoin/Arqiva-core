package org.emerjoin.arqiva.core;

/**
 * @author Mário Júnior
 */
public class ArqivaException extends RuntimeException {

    public ArqivaException(String message){

        super(message);

    }

    public ArqivaException(String message, Throwable cause){

        super(message,cause);

    }

}
