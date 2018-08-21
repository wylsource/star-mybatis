package com.starylwu.mybatis.session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Objects;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:40
 * @Description:
 */
public class SimpleExecutor implements Executor{

    @Override
    public <E> E query(String sql, Object parameter){
        try {
            //获取数据库连接&&执行sql
            Connection connection = getConnection();
            String executeSql = String.format(sql, String.valueOf(parameter));
            PreparedStatement preparedStatement = connection.prepareStatement(executeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            //封装结果集（目前支持查询单个）--使用反射
            String returnType = TestMapperXml.methodSqlMapping.get("selectOne_returnType");
            Class<?> clazz = Class.forName(returnType);
            Object instance = clazz.newInstance();
            Reflector reflector = new Reflector(clazz);
            resultSet.next();
            TestMapperXml.fieldMapping.entrySet().stream()
                    .forEach(entry -> {
                        Method setMethod = reflector.setMethods.get(entry.getKey());
                        if (Objects.nonNull(setMethod)){
                            try {
                                setMethod.invoke(instance, resultSet.getObject(entry.getValue()));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            return (E)instance;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // see mybatis Reflector.class


    private Connection getConnection() throws SQLException {
        String URL="jdbc:mysql://127.0.0.1:3306/star-mybatis?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER="root";
        String PASSWORD="root";
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2.获得数据库链接
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
