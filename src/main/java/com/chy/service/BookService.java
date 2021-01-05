package com.chy.service;

import com.chy.pojo.Books;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public interface BookService {

    List<Books> queryAllBooks();

    Books selectBookById(int id);

    int updateBook(Books books);

    int addBooks(Books books);

    int deleteBookById(int id);

}
