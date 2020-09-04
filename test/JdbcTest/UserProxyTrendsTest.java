package JdbcTest;

import com.study.wqh.jdbc.proxy.IUserDao;
import com.study.wqh.jdbc.proxy.Impl.UserDaoImpl;
import com.study.wqh.jdbc.proxy.Impl.UserDaoProxyThrends;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author: 王其浩
 * @ClassName: UserProxyTrendsTest
 * @Description:
 * @Date 2020/9/4
 * @version:
 */
public class UserProxyTrendsTest {

    @Test
    public void test(){
        IUserDao userDao = new UserDaoImpl();

        UserDaoProxyThrends userDaoProxyThrends = new UserDaoProxyThrends(userDao);

        IUserDao userProxy = (IUserDao) Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(),userDaoProxyThrends);

        userProxy.del(10);

        System.out.println("==============");

        System.out.println(userProxy.getName());
    }
}
