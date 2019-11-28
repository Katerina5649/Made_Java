package com.company;

import java.util.Map;

public class JsonSerializer implements Serializer {

    public StringBuilder mapToJson(Map<String, Object> map, int tabsCount) {
        StringBuilder result = new StringBuilder("");
        StringBuilder tabs = new StringBuilder("");
        for (int i = 0; i < tabsCount; i++)
            tabs.append("\t");

        for (Map.Entry<String, Object> e : map.entrySet()) {
            result.append(tabs + " \"" + e.getKey() + " \"");
            result.append(" : ");
            if (e.getValue() instanceof Map) {
                Map<String, Object> imaginaryMap = (Map) e.getValue();
                if (imaginaryMap.containsKey("0")) {
                    result.append("[" + "\n");
                    for (Object m : imaginaryMap.values()) {
                        if (m instanceof Map) {
                            result.append(tabs);
                            result.append("\t");
                            result.append(mapToJson((Map<String, Object>) m, tabsCount + 2));
                        } else {
                            result.append("\t");
                            result.append(tabs + " \"" + m.toString() + " \",\n");
                        }

                    }
                    result.append("]\n");

                } else {
                    result.append("{" + "\n");
                    result.append(mapToJson((Map<String, Object>) e.getValue(), tabsCount + 1));
                    result.append("}\n");
                }
            } else {
                result.append(" \"" + e.getValue().toString() + " \"" + "\n");
            }

        }
        return result;
    }

    @Override
    public StringBuilder serialize(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder("");
        Map map = Serializer.getAllFieldsByMap(o);
        result.append("{\n");
        result.append(mapToJson(map, 0));
        result.append("}");
        return result;
    }
}
