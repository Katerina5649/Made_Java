package com.company;

import java.util.*;

public class XmlSerializer implements Serializer {

    public StringBuilder mapToXml(Map<String, Object> map, int tabsCount) {
        StringBuilder result = new StringBuilder("");
        StringBuilder tabs = new StringBuilder("");
        for (int i = 0; i < tabsCount; i++)
            tabs.append("\t");
        for (Map.Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() instanceof Map) {
                result.append(tabs + "<" + e.getKey() + ">" + "\n");
                result.append(this.mapToXml((Map<String, Object>) e.getValue(), tabsCount + 1));
                result.append(tabs + "</" + e.getKey() + "> \n");
            } else {
                result.append(tabs + "<" + e.getKey() + ">" + e.getValue().toString() + "</" + e.getKey() + ">" + "\n");
            }

        }
        return result;
    }

    @Override
    public StringBuilder serialize(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder("");
        Map<String, Object> map = Serializer.getAllFieldsByMap(o);
        String className = o.getClass().getName().substring(o.getClass().getName().indexOf("$") + 1);
        result.append("<" + className + ">\n");
        result.append(mapToXml(map, 1));
        result.append("</" + className + ">\n");
        return result;
    }

}

