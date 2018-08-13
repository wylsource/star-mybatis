package mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/11 10:39
 * @Description:
 */
public class MybatisTest {

    private static SqlSessionFactory sqlMapper;


    @BeforeClass
    public static void setup() throws Exception {
        final String resource = "MapperManger.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlMapper = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void test(){
        SqlSession sqlSession = sqlMapper.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
    }
}
