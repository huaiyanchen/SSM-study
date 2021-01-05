import com.chy.dao.BooksMapper;
import com.chy.pojo.Books;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class DaoTest {

    @Test
    public void testDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        BooksMapper booksMapper = context.getBean("BooksMapper", BooksMapper.class);
        List<Books> books = booksMapper.queryAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testDao2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        BooksMapper booksMapper = context.getBean("BooksMapper", BooksMapper.class);
        List<Books> books = booksMapper.queryAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testDao3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        BooksMapper booksMapper = context.getBean("BooksMapper", BooksMapper.class);
        Books books = booksMapper.selectBookById(1);
        System.out.println(books);

    }
}
