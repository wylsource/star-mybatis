package com.starylwu.mybatis.session;

import org.apache.ibatis.reflection.ReflectionException;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

import static com.starylwu.mybatis.session.TestMapperXml.fieldMapping;

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

    /**
     * 构造的时候初始化对应class的get/set方法
     * @param cls
     */
    public Reflector(Class<?> cls){
        Set<String> fieldSet = fieldMapping.keySet();
        fieldSet.stream().forEach(fieldName -> {
            try {
                String setMethodName = setMethodFix + toUpFirst(fieldName);
                String getMethodName = getMethodFix + toUpFirst(fieldName);
                Method[] methods = cls.getMethods();
                for (int j=0; j< methods.length; j++){
                    Method method = methods[j];
                    if (Objects.equals(setMethodName,method.getName())){
                        setMethods.put(fieldName, method);
                    }
                    if (Objects.equals(getMethodName,method.getName())){
                        getMethods.put(fieldName, method);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 将首字母大写
     * @param value
     * @return
     */
    private String toUpFirst(String value){
        char[] chars = value.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
