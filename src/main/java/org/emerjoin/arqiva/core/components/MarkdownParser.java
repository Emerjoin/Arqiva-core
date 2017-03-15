package org.emerjoin.arqiva.core.components;

/**
 * @author Mário Júnior
 */
public interface MarkdownParser extends BuildComponent {

    public String toHTML(String markdown);

}
