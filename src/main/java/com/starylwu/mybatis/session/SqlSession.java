package com.starylwu.mybatis.session;

import java.lang.reflect.Proxy;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:16
 * @Description:
 */
public class SqlSession {

    private Executor executor = new SimpleExecutor();

    // TODO configuration

    public <T> T selectOne(String statement, Object parameter){

        // use executor select
        return executor.query(statement, parameter);
    }

    public <T> T getMapper(Class<T> clazz){

        // use proxy get mapper
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new SqlSessionInterceptor(this, clazz));
    }
}
