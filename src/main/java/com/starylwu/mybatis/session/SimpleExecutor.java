package com.starylwu.mybatis.session;

import java.lang.reflect.Field;
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
            Connection connection = getConnection();
            String executeSql = String.format(sql, String.valueOf(parameter));
            PreparedStatement preparedStatement = connection.prepareStatement(executeSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            String returnType = TestMapperXml.methodSqlMapping.get("selectOne_returnType");
            Class<?> aClass = Class.forName(returnType);
            Field[] declaredFields = aClass.getDeclaredFields();
            Object instance = aClass.newInstance();
            int index = 0;
            ResultSetMetaData metaData = resultSet.getMetaData();
            Reflector reflector = new Reflector(aClass);
            while (resultSet.next() && index < declaredFields.length){
                Field field = declaredFields[index];
                Method method = reflector.setMethods.get(field.getName());
                if (Objects.nonNull(metaData)){
                    method.invoke(instance, resultSet.getString(++index));
                }
            }
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
