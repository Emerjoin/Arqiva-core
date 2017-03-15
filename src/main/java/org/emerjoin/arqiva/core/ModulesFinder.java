package org.emerjoin.arqiva.core;

import java.util.function.Function;

/**
 * @author Mário Júnior
 */
public interface ModulesFinder {


    public void findModules(Function<String,Void> starter);


}
