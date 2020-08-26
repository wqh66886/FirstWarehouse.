package JdbcTest;

import com.study.wqh.jdbc.day04.dao.Impl.ArticleDaoImpl;
import com.study.wqh.jdbc.day04.entity.Article;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author: 王其浩
 * @ClassName: ArticleDaoImplTest
 * @Description:
 * @Date 2020/8/26
 * @version:
 */
public class ArticleDaoImplTest {

    private ArticleDaoImpl articleDao = new ArticleDaoImpl();

    @Test
    public void TestfinaAll(){
        List<Article> articleList = articleDao.findAll();
        articleList.forEach(e-> System.out.println(e));
    }

    @Test
    public void TestSave(){
        Article article = new Article("红楼梦","30MB","http://localhost:8080/Grant",new Date());
        articleDao.Save(article);
    }

    @Test
    public void TestGetByID(){
        Article article = articleDao.getById(2);
        System.out.println(article);
    }

    @Test
    public void TestDelById(){
        int row = articleDao.delById(8);
        if(row > 0){
            System.out.println("删除成功!");
        }else{
            System.out.println("删除失败!");
        }
    }

    @Test
    public void TestRemoveById(){
        articleDao.removeById(7);
    }
}
