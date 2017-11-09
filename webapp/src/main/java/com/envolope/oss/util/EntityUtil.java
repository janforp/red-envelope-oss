package com.envolope.oss.util;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by Jan on 16/10/31.
 * 实体类工具方法
 */
public class EntityUtil implements Serializable{

    public static Object nullToEmptyString(Object object){

        if (object == null) {

            return object;
        }
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        if (fields == null || fields.length == 0) {

            return object;
        }

        for (Field field : fields) {

            try {

                field.setAccessible(true);

                Object value = field.get(object);

                Class<?> fieldType = field.getType();

                if (value == null && fieldType.isAssignableFrom(String.class)){

                    field.set(object,"");

                }

            }catch (Exception e){

                e.printStackTrace();
            }
        }

        return object;
    }

    /**
     * 把空字符串转换为null
     * 数字参数不能为空
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T emptyStringToNull(T t){

        try {
            Class<T> clz = (Class<T>) t.getClass();

            Field[] fields = clz.getDeclaredFields();
            if (fields != null && fields.length >0){

                for (Field fs:fields) {
                    fs.setAccessible(true);
                    Object value = fs.get(t);
                    if (StringUtil.isEmpty(value)){
                        fs.set(t,null);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("类不存在");
            e.printStackTrace();
        }

        return t;
    }
}
