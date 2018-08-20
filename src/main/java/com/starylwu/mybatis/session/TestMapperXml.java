package com.starylwu.mybatis.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:25
 * @Description:
 */
public class TestMapperXml {

    public static final String nameSpace = "com.starylwu.mybatis.session.TestMapper";

    public static final ConcurrentMap<String, String> methodSqlMapping = new ConcurrentHashMap<>();
    public static final ConcurrentMap<String, String> fieldMapping = new ConcurrentHashMap<>();

    static {
        methodSqlMapping.putIfAbsent("selectOne", "select * from user where user_id = %s");
        methodSqlMapping.putIfAbsent("selectOne_returnType", "com.starylwu.mybatis.session.Test");
        fieldMapping.putIfAbsent("userId", "user_id");
        fieldMapping.putIfAbsent("userName", "user_name");
        fieldMapping.putIfAbsent("userPassword", "user_password");
    }
}
