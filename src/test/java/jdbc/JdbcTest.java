package jdbc;

import java.sql.*;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/11 10:22
 * @Description:
 */
public class JdbcTest {

    public static void main(String[] args) throws Exception{

        String URL="jdbc:mysql://127.0.0.1:3306/star-mybatis?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER="root";
        String PASSWORD="root";
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库链接
        Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        String sql = "select * from user";
        PreparedStatement st= conn.prepareStatement(sql);
        ResultSet rs=st.executeQuery();
        //4.处理数据库的返回结果(使用ResultSet类)
        while(rs.next()){
            System.out.println(rs.getString("user_name")+" "
                    +rs.getString("user_password"));
        }
        //关闭资源
        rs.close();
        st.close();
        conn.close();
    }
}
