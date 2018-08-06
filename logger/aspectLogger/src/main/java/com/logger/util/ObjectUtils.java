package com.logger.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Objects;

public class ObjectUtils {
    public static String getFieldNamesAndValues(final Object obj, boolean publicOnly) {
        StringBuilder sb = new StringBuilder();
        try {
            if (Objects.nonNull(obj)) {
                Class<? extends Object> c1 = obj.getClass();
                Field[] fields = c1.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    String name = fields[i].getName();
                    if (publicOnly) {
                        if (Modifier.isPublic(fields[i].getModifiers())) {
                            Object value = null;

                            value = fields[i].get(obj);
                            sb.append("name: " + name + "; " + "value:" + value + ";");
                        }
                    } else {
                        fields[i].setAccessible(true);
                        Object value = fields[i].get(obj);
                        sb.append("name: " + name + "; " + "value:" + value + ";");
                    }
                }
            } else {
                sb.append(" NO DATA ");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
