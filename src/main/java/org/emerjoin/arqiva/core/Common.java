package org.emerjoin.arqiva.core;

/**
 * @author Mário Júnior
 */
public class Common {

    public static String getValidName(Named named, Class clazzName){

        if(named.value().trim().length()<3)
            throw new ArqivaException(String.format("Invalid @%s value set on class %s",
                    Named.class.getSimpleName(), clazzName.getCanonicalName()));

        return named.value();

    }

}
