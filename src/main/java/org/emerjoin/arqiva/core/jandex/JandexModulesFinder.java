package org.emerjoin.arqiva.core.jandex;

import org.emerjoin.arqiva.core.ArqivaException;
import org.emerjoin.arqiva.core.Module;
import org.emerjoin.arqiva.core.ModulesFinder;
import org.emerjoin.arqiva.core.MustBeImplementedException;
import org.jboss.jandex.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Mário Júnior
 */
public class JandexModulesFinder implements ModulesFinder {

    private static final Logger log = LoggerFactory.getLogger(JandexModulesFinder.class);
    //private static List<String> moduleClasses = null;

    public void findModules(Function<String, Void> starter) {

        ClassLoader currentClassLoader = this.getClass().getClassLoader();
        if(!(currentClassLoader instanceof URLClassLoader)) {
            log.warn(String.format("Can't scan a %s classloader. URLClassLoader required", currentClassLoader.getClass().getCanonicalName()));
            return;
        }

        URLClassLoader urlClassLoader = (URLClassLoader) currentClassLoader;
        Indexer indexer = new Indexer();

        try {

            for (URL url : urlClassLoader.getURLs()) {
                String filePath = url.getFile();

                File file = new File(filePath);
                if (file.isDirectory())
                    continue;

                File jarFile = new File(url.getFile());
                File tempFile = File.createTempFile(jarFile.getName(), "jandex");

                try {

                    log.debug(String.format("Jandex Indexing JAR file %s", file.getAbsolutePath()));
                    JarIndexer.createJarIndex(jarFile, indexer, tempFile, false, false, false).getIndex();

                }finally {
                    if(tempFile.exists())
                        tempFile.delete();
                }


            }

            Set<ClassInfo> modulesClassesInfo = indexer.complete().getAllKnownImplementors(DotName.createSimple(Module.class.getCanonicalName()));
            for (ClassInfo classInfo : modulesClassesInfo) {
                String moduleClazz = classInfo.name().toString();
                log.info(String.format("Detected Module : %s", moduleClazz));
                starter.apply(moduleClazz);
            }

        }catch (IOException ex){

            new ArqivaException("Libraries indexation failed",ex);

        }

    }
}
