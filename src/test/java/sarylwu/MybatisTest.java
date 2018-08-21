package sarylwu;

import com.starylwu.mybatis.session.SqlSession;
import com.starylwu.mybatis.session.Test;
import com.starylwu.mybatis.session.TestMapper;

import java.util.List;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:57
 * @Description:
 */
public class MybatisTest {

    @org.junit.Test
    public void test() {
        SqlSession sqlSession = new SqlSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Test test = mapper.selectOne(1);
        System.out.println(test.toString());
    }
}
