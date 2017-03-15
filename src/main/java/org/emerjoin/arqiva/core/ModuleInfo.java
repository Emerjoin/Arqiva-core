package org.emerjoin.arqiva.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

/**
 * @author Mário Júnior
 */
public class ModuleInfo {

    private String name;
    private String clazz;

    public ModuleInfo(String clazzName){

        this.clazz = clazzName;

    }

    public String getClazz(){

        return clazz;

    }

    public String getName(){

        return name;

    }

    public Module construct(){

        try {

            Class moduleClazz = Class.forName(clazz);
            Annotation declaredAnnotation = moduleClazz.getDeclaredAnnotation(Named.class);
            if(declaredAnnotation==null)
                throw new ArqivaException(String.format("No %s annotation was found on module class %s",Named.class.getSimpleName(),clazz));

            name = Common.getValidName((Named) declaredAnnotation,moduleClazz);

            Constructor constructor = moduleClazz.getDeclaredConstructor(null);
            Module module = (Module) constructor.newInstance();
            module.postConstruct();
            return module;

        }catch (Throwable t){

            throw new ArqivaException(String.format("Failed to construct module with name %",name),t);
        }

    }

}
