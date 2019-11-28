package com.company;

import java.lang.reflect.Field;
import java.util.*;

public interface Serializer {
    public  StringBuilder serialize(Object o) throws IllegalAccessException;

    public static boolean isPrimitive(Object o){
        return  (o.getClass().isPrimitive() || (o instanceof Number) || (o instanceof Boolean)
                || (o instanceof String)) ;
    }

    public static boolean isArray(Class<?> type) {
        return type.toString().replace("class ", "").startsWith("[") || (type == AbstractList.class)
                || (type == AbstractSequentialList.class);
    }

    public static Map getAllFieldsByMap(Object o) throws IllegalAccessException {
        Map result = new HashMap();
        if  (isArray(o.getClass())){
            o = (Object[]) o;
        }
        if (o instanceof AbstractCollection) {
            int i = 0;
            for (Object e : (AbstractCollection) o) {
                if (Serializer.isPrimitive(e))
                    result.put(Integer.toString(i), e.toString());
                else
                    result.put(Integer.toString(i), getAllFieldsByMap(e));
                i++;
            }
            return result;
        }

        Class<?> clazz = o.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //ставлю разрешение получать приватные поля
            declaredField.setAccessible(true);
            if (Serializer.isPrimitive(declaredField.get(o))) {
                result.put(declaredField.getName(), declaredField.get(o).toString());
            }
            else {
                result.put(declaredField.getName(), getAllFieldsByMap(declaredField.get(o)));
            }
        }
        return result;
    }

    }


//type.componentType