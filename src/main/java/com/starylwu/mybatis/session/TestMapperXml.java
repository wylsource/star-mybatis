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

    static {
        methodSqlMapping.putIfAbsent("selectOne", "select * from user where user_id = %s");
    }
}
