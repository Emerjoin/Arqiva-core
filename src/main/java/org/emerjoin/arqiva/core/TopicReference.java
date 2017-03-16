package org.emerjoin.arqiva.core;


import java.io.File;

/**
 * @author Mário Júnior
 */
public class TopicReference {

    private String markdownFilePath;
    private String readUrl;
    private String title;
    private String position;
    private int markdownFileOrderNumber;

    public String getMarkdownFilePath() {
        return markdownFilePath;
    }

    public void setMarkdownFilePath(String markdownFilePath) {
        this.markdownFilePath = markdownFilePath;
    }

    public String getReadUrl() {
        return readUrl;
    }

    public void setReadUrl(String readUrl) {
        this.readUrl = readUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMarkdownFileOrderNumber() {
        return markdownFileOrderNumber;
    }

    public void setMarkdownFileOrderNumber(int markdownFileOrderNumber) {
        this.markdownFileOrderNumber = markdownFileOrderNumber;
    }

    public String getMarkdownContent(){

        //TODO: Implement
        throw new MustBeImplementedException();

    }

    public static TopicReference get(String url){

        //TODO: Implement
        throw new MustBeImplementedException();

    }

    public static TopicReference get(File markdownFile){

        //TODO: Implement
        throw new MustBeImplementedException();

    }
}
