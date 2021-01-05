import com.chy.dao.BooksMapper;
import com.chy.pojo.Books;
import com.chy.service.BookService;
import com.chy.service.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class ServiceTest {
    @Autowired
    private BooksMapper booksMapper;

    @Test
    public void testServiceImpl() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        BookService booksMapper = context.getBean("bookServiceImpl", BookServiceImpl.class);
        List<Books> books = booksMapper.queryAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
        BookService booksMapper = context.getBean("bookServiceImpl", BookServiceImpl.class);
        List<Books> books = booksMapper.queryAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void testServiceAuto() {
        List<Books> books = booksMapper.queryAllBooks();
        for (Books book : books) {
            System.out.println(book);
        }

    }


}
