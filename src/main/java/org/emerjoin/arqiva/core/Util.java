package org.emerjoin.arqiva.core;

import java.io.File;

/**
 * Created by Mário on 3/20/2017.
 */
public class Util {

    public static String[] splitPath(String path){

        String separatorChat = File.separator;
        if(separatorChat.equals("/")) {
            return path.split("/");
        }else {
            return path.split("\\\\");
        }

    }

}
