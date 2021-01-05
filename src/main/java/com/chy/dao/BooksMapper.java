package com.chy.dao;

import com.chy.pojo.Books;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BooksMapper")
public interface BooksMapper {

    List<Books> queryAllBooks();

    Books selectBookById(@Param("id") int id);

    int updateBook(Books books);

    int addBooks(Books books);

    int deleteBookById(@Param("id") int id);


}
