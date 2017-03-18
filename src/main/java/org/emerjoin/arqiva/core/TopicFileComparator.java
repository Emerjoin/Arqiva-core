package org.emerjoin.arqiva.core;

import java.io.File;
import java.util.Comparator;

/**
 * @author Mário Júnior
 */
public class TopicFileComparator implements Comparator<File> {

    public int compare(File fileX, File fileY) {

        String fileXName = fileX.getName();
        int fileXNumber = Integer.parseInt(fileXName.substring(0,fileXName.indexOf('_')));

        String fileYName = fileY.getName();
        int fileYNumber = Integer.parseInt(fileYName.substring(0,fileYName.indexOf('_')));

        return ((fileXNumber<fileYNumber)?-1:1);

    }
}
