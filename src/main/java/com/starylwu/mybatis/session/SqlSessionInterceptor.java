package com.starylwu.mybatis.session;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:23
 * @Description:
 */
public class SqlSessionInterceptor<T> implements InvocationHandler {

    private final SqlSession sqlSession;

    private final Class<T> mapperInterface;

    public SqlSessionInterceptor(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Objects.equals(method.getDeclaringClass().getName(),TestMapperXml.nameSpace)){
            String methodSql = TestMapperXml.methodSqlMapping.getOrDefault(method.getName(), null);
            if (Objects.nonNull(method)){
                System.out.println(String.format("SQL=[ %s ], parameter=[ %s ]", methodSql, args[0]));
                return sqlSession.selectOne(methodSql, String.valueOf(args[0]));
            }
        }
        return null;
    }
}
