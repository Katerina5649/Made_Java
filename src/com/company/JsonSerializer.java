package com.company;

import java.lang.reflect.Field;
import java.util.AbstractCollection;
import java.util.List;

public class JsonSerializer implements Serializer {

    @Override
    public StringBuilder serialize(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder("");
        //-----------------------------------------
        String firstStart = "\"";
        String firstEnd = "\" : ";
        String secondEnd = "\",";
        //---------------------------------------
        if (Serializer.isPrimitive(o))
            return new StringBuilder(o.toString());
        if (o instanceof AbstractCollection){
            result.append("[ \n");
            for (Object e : (AbstractCollection) o){
                result.append(firstStart
                        + serialize(e) + secondEnd + "\n");
            }
            result.append("]");
            return result;
        }

        Class<?> clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //ставлю разрешение получать приватные поля
            declaredField.setAccessible(true);
            if (Serializer.isPrimitive(declaredField.get(o)))
                result.append(firstStart + declaredField.getName() + firstEnd
                        + String.valueOf(declaredField.get(o)) +  secondEnd + "\n");
            if (declaredField.get(o) instanceof AbstractCollection) {
                result.append(firstStart + declaredField.getName() + firstEnd);
                result.append(serialize(declaredField.get(o)));
            }
            else {
                result.append(firstStart + declaredField.getName() + firstEnd);
                result.append("{\n");
                result.append("\t" + serialize(declaredField.get(o)) + secondEnd + "\n");
                result.append("}, \n");
            }

        }

        return result;
    }
}
