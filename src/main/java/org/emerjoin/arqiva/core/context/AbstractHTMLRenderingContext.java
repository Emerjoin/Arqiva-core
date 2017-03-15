package org.emerjoin.arqiva.core.context;

/**
 * @author Mário Júnior
 */
public abstract class AbstractHTMLRenderingContext extends AbstractRenderingContext implements HTMLRenderingContext {

    private String html = null;

    public AbstractHTMLRenderingContext(ProjectContext root, String html) {
        super(root);
        this.html = html;
    }

    public String getHtml() {

        return html;

    }

    public void updateHtml(String template) {

        this.html = template;

    }
}
