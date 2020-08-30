package JdbcTest;

import com.study.wqh.jdbc.day04.dao.IUserDao;
import com.study.wqh.jdbc.day04.dao.Impl.UserDaoImpl;

import com.study.wqh.jdbc.day04.entity.StatusEnum;
import com.study.wqh.jdbc.day04.entity.User;
import org.junit.Test;

/**
 * @author: 王其浩
 * @ClassName: UserDaoTest
 * @Description:
 * @Date 2020/8/30
 * @version:
 */
public class UserDaoTest {

    private IUserDao userDao = new UserDaoImpl();

    @Test
    public void TestUpdate(){
//        User user = new User();
//
//        user.setId(3);
//
//        user.setUsername("王其浩");
//        user.setPassword("1234567");
//        user.setStatus(StatusEnum.USER);

        User user = userDao.getById(3);
        if(null != user){
            user.setUsername("汤姆.斯坦福");
        user.setStatus(StatusEnum.USER);
        }

        userDao.updata(user);
        System.out.println(user);
    }
}
