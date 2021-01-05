package com.chy.service;

import com.chy.dao.BooksMapper;
import com.chy.pojo.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksMapper booksMapper;

    public void setBooksMapper(BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
    }

    @Override
    public List<Books> queryAllBooks() {
        return booksMapper.queryAllBooks();
    }

    @Override
    public Books selectBookById(int id) {
        return booksMapper.selectBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        return booksMapper.updateBook(books);
    }

    @Override
    public int addBooks(Books books) {
        return booksMapper.addBooks(books);
    }

    @Override
    public int deleteBookById(int id) {
        return booksMapper.deleteBookById(id);
    }

}
