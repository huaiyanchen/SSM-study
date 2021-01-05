package com.chy.controller;

import com.chy.pojo.Books;
import com.chy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BooksController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/allBook")
    public String list(Model model) {

        List<Books> list = bookService.queryAllBooks();
        Model list1 = model.addAttribute("list", list);

        return list1.toString();
    }

}
