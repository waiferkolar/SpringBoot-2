package coder.TM65.controllers;

import coder.TM65.daos.BookDao;
import coder.TM65.daos.PageDao;
import coder.TM65.models.Book;
import coder.TM65.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pages")
public class PagesController {
    @Autowired
    PageDao pageDao;
    @Autowired
    BookDao bookDao;

    @GetMapping("/")
    public String all(Model model) {
        List<Page> pages = pageDao.findAll();
        for (Page page : pages) {
            System.out.println(page);
        }
        model.addAttribute("pages", pages);
        return "pages/all";
    }

    @GetMapping("/create/{book_id}")
    public String create(@PathVariable int book_id, Model model) {
        model.addAttribute("page", new Page());
        return "pages/create";
    }

    @PostMapping("/create/{book_id}")
    public String createNow(@PathVariable long book_id, @ModelAttribute Page page) {
        Book book = bookDao.findById(book_id).orElse(null);
        page.setBook(book);
        pageDao.save(page);
        return "redirect:/pages/";
    }
}
