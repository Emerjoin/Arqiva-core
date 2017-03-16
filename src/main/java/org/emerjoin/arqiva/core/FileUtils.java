package org.emerjoin.arqiva.core;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Mário Júnior
 */
public class FileUtils {


    public static void putFileContents(File file, String content){

        if(file.isDirectory())
            throw new IllegalArgumentException("File must not be a directory");

        if(!file.exists())
            file.getParentFile().mkdirs(); //Create all parent directories

        FileOutputStream fileOutputStream = null;

        try {

            fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.print(content);
            printStream.flush();


        }catch (IOException ex){

            throw new ArqivaException(String.format("Failed to put text content into file %s",file.getAbsolutePath()),ex);

        }finally {

            if(fileOutputStream!=null)
                try {
                    fileOutputStream.close();

                }catch (IOException ex){}

        }

    }


    public static String getFileContents(File file){

        String content = "";
        FileInputStream fileInputStream = null;
        try{

            fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            boolean firstLine = true;
            while (scanner.hasNextLine()){

                content = scanner.nextLine();
                if(!firstLine)
                    content = content+"\n";
                else
                    firstLine = false;

            }

        }catch (IOException ex){

            throw new ArqivaException(String.format("Failed to read contents of file %s",file.getAbsolutePath()),ex);

        }finally {

            try {
                fileInputStream.close();
            }catch (IOException ex){}

        }

        return content;
    }


    public static void copyFileTo(File origin, File dest){

        //TODO: Implement
        throw new NotImplementedException();

    }


    public static void copyFilesFrom(File directory, File destination, Function<File,Void> filter){

        //TODO: Implement
        throw new NotImplementedException();

    }

    public static boolean matchFileContents(File a, File b){

        //TODO: Implement
        throw new NotImplementedException();

    }

}
