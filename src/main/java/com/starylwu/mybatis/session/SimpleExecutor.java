package com.starylwu.mybatis.session;

import java.sql.*;

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
            Test test  = new Test();

            while (resultSet.next()){
                test.setUserId(resultSet.getInt(1));
                test.setUserName(resultSet.getString(2));
                test.setUserPassword(resultSet.getString(3));
            }
            return (E)test;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
