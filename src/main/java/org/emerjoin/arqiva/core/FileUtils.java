package org.emerjoin.arqiva.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author Mário Júnior
 */
public class FileUtils {

    public static int MAX_FILE_COPY_BUFFER = 1024*5; //5 Megabytes by default
    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

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

                content += scanner.nextLine();
                if(!firstLine)
                    content += "\n";
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

        if(origin==null||dest==null)
            throw new NullPointerException("The origin File cant be null, nor the destination one");

        if(!origin.exists())
            throw new IllegalArgumentException(String.format("Origin file %s does not exist",origin.getAbsolutePath()));

        File parentDestFile = dest.getParentFile();
        if(!parentDestFile.exists())//Create the sub-directories if they don't exist yet
            parentDestFile.mkdirs();

        InputStream fileInput = null;
        OutputStream fileOutput = null;

        try{

            fileInput = new BufferedInputStream(new FileInputStream(origin),MAX_FILE_COPY_BUFFER);
            fileOutput = new BufferedOutputStream(new BufferedOutputStream(new FileOutputStream(dest),MAX_FILE_COPY_BUFFER));

            int readByte = -1;
            while((readByte = fileInput.read())!=-1)
                fileOutput.write(readByte);

        }catch (IOException ex){

            throw new ArqivaException(String.format("Failed to copy file %s to %s",origin.getAbsolutePath(),dest.getAbsolutePath()));

        }finally {

            try {

                if (fileInput != null)
                    fileInput.close();
                if (fileOutput != null)
                    fileOutput.close();

            }catch (IOException ex){

                log.error("Error closing file streams",ex);

            }

        }



    }

}
