package com.starylwu.mybatis.session;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: WuYuLong
 * @Date: Create in 18:49 2018/8/20
 * @DESC:
 */
public class Reflector {

    public final Map<String, Method> setMethods = new HashMap<String, Method>();
    public final Map<String, Method> getMethods = new HashMap<String, Method>();
    public final Map<String, Class<?>> setTypes = new HashMap<String, Class<?>>();
    public final Map<String, Class<?>> getTypes = new HashMap<String, Class<?>>();

    private static final String setMethodFix = "set";
    private static final String getMethodFix = "get";

    public Reflector(Class<?> cls){
        Field[] declaredFields = cls.getDeclaredFields();
        for (int i = 0;i < declaredFields.length; i++){
            try {
                String fieldName = declaredFields[i].getName();
                String upFirstFieldName = toUpFirst(fieldName);
                Method setMethod = cls.getDeclaredMethod(setMethodFix + upFirstFieldName, cls);
                Method getMethod = cls.getDeclaredMethod(getMethodFix + upFirstFieldName, cls);
                setMethods.put(fieldName, setMethod);
                getMethods.put(fieldName, getMethod);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private String toUpFirst(String value){
        char[] chars = value.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
