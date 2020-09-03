package JdbcTest;

import com.study.wqh.jdbc.day04.dao.ICommentDao;
import com.study.wqh.jdbc.day04.dao.Impl.CommentDaoImpl;
import com.study.wqh.jdbc.day04.dto.CommentDTO;
import com.study.wqh.jdbc.day04.entity.Comment;
import org.junit.Test;

/**
 * @author: 王其浩
 * @ClassName: COmmentDaoTest
 * @Description:
 * @Date 2020/9/3
 * @version:
 */
public class CommentDaoTest {

    private ICommentDao commentDao = new CommentDaoImpl();

    @Test
    public void testFindByArticle(){
        CommentDTO commentDTO = commentDao.findByArticle(1);
        System.out.println("当前作品:"+commentDTO.getArticle());
        for (Comment comment : commentDTO.getCommentList()) {
            System.out.println("\t"+"当前作品的评论"+comment);
        }
    }
}
