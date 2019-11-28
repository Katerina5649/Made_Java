package com.company;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.List;
import java.util.Map;

public class JsonSerializer implements Serializer {

    public StringBuilder mapToJson(Map<String, Object> map, int tabsCount){
        StringBuilder result = new StringBuilder("");
        StringBuilder tabs = new StringBuilder("");
        for (int i = 0; i < tabsCount; i++ )
            tabs.append("\t");
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() instanceof Map) {
                result.append( e.getKey() + " : {" + "\n");
                result.append(mapToJson((Map<String, Object>) e.getValue(), tabsCount+1));
                result.append("}\n");
            } else {
                result.append(tabs  + e.getKey() + " : " + e.getValue().toString() +  "\n");
            }

        }
        return result;
    }
    @Override
    public StringBuilder serialize(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder("");
        Map map  = Serializer.getAllFieldsByMap(o);
        result.append("{");
        result.append(mapToJson(map, 0));
        result.append("}");
        return result;
    }
}
