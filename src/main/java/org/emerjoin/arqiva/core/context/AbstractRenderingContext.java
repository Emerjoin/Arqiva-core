package org.emerjoin.arqiva.core.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mário Júnior
 */
public  class AbstractRenderingContext implements RenderingContext {

    private Map<String,Object> values = new HashMap<String,Object>();
    private ProjectContext root = null;

    public AbstractRenderingContext(ProjectContext root){

        this.root = root;
    }

    public ProjectContext getRootContext() {

        return root;
    }

    public void setValue(String name, Object value) {

        values.put(name,value);

    }

    public boolean hasValue(String name) {

        return values.containsKey(name)||root.hasValue(name);

    }

    public Object getValue(String name) {
        if(values.containsKey(name))
            return values.get(name);

        return root.getValue(name);
    }

    public Object getValue(String name, Object defaultValue) {

        if(!hasValue(name))
            return defaultValue;

        return getValue(name);

    }

    public Map<String, Object> getValues() {
        Map<String,Object> _merged = new HashMap<String, Object>();
        _merged.putAll(root.getValues());
        _merged.putAll(values);
        return _merged;
    }
}
