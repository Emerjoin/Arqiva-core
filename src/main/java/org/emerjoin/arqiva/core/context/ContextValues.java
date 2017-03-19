package org.emerjoin.arqiva.core.context;

import java.util.Map;

/**
 * @author Mário Júnior
 */
public interface ContextValues {

    public void setValue(String name, Object value);
    public boolean hasValue(String name);
    public Object getValue(String name);
    public Object getValue(String name, Object defaultValue);
    public Map<String,Object> getValues();

}
