package org.emerjoin.arqiva.core.context;

/**
 * @author Mário Júnior
 */
public interface MarkdownRenderingContext extends HTMLRenderingContext {

    public void updateMarkdown(String markdown);
    public String getMarkdown();
    public String getCompiledMarkdown();

}
