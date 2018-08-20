package sarylwu;

import com.starylwu.mybatis.session.Test;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: WuYuLong
 * @Date: Create in 14:28 2018/8/20
 * @DESC:
 */
public class TypeTest {

    public <E extends Object> E method(){
        return null;
    }

    @org.junit.Test
    public void test(){
        try {
            Method method = TypeTest.class.getMethod("method", null);
            Type returnType = method.getGenericReturnType();
            if (returnType instanceof TypeVariable) {
                System.out.println("泛型类型");
            } else if (returnType instanceof ParameterizedType) {
                System.out.println("带泛型的对象");
            } else if (returnType instanceof GenericArrayType) {
                System.out.println("带泛型的数组");
            } else if (returnType instanceof Class){
                System.out.println("Class");
            }else {
                System.out.println("未知");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
