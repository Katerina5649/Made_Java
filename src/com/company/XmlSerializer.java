package com.company;
//доделать https://habr.com/ru/post/207360/

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

//паттерны стратегии
public class XmlSerializer implements Serializer {

    @Override
    public StringBuilder serialize(Object o) throws IllegalAccessException {
        StringBuilder result = new StringBuilder("");
        //-----------------------------------------
        String firstStart = "<";
        String firstEnd = ">";
        String secondStart = "</";
        String secondEnd = ">";
        //---------------------------------------
        //if (Serializer.isPrimitive(o))
        //    return new StringBuilder(o.toString());
        if (o instanceof AbstractCollection) {
            int i = 0;
            for (Object e : (AbstractCollection) o) {
                result.append(firstStart + i + firstEnd
                        + serialize(e) + secondStart + i + secondEnd + "\n");
                i++;
            }
            return result;
        }

        Class<?> clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //ставлю разрешение получать приватные поля
            declaredField.setAccessible(true);
            if (Serializer.isPrimitive(declaredField.get(o)))
                result.append(firstStart + declaredField.getName() + firstEnd
                        + String.valueOf(declaredField.get(o)) + secondStart + declaredField.getName() + secondEnd + "\n");
            else {
                result.append(firstStart + declaredField.getName() + firstEnd);
                result.append("\t" + serialize(declaredField.get(o)));
                result.append(secondStart + declaredField.getName() + secondEnd + "\n");
            }

        }

        return result;
    }

}

