package org.emerjoin.arqiva.core.comp;

/**
 * @author Mário Júnior
 */
public interface MarkdownParser extends BuildComponent {

    public String toHTML(String markdown);

}
