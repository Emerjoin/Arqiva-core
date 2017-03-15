package org.emerjoin.arqiva.core.context;

/**
 * @author Mário Júnior
 */
public interface HTMLRenderingContext extends RenderingContext {

    public String getHtml();
    public void updateHtml(String template);

}
