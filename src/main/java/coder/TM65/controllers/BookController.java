package coder.TM65.controllers;

import coder.TM65.daos.BookDao;
import coder.TM65.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookDao bookDao;

    @GetMapping("/")
    public String all(Model model) {
        List<Book> books = bookDao.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
        model.addAttribute("books", books);
        return "book/all";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("book", new Book());
        return "book/create";
    }

    @PostMapping("/create")
    public String createNow(@ModelAttribute Book book) {
        bookDao.save(book);
        return "redirect:/book/";
    }

}
