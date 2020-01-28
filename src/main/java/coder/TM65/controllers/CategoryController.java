package coder.TM65.controllers;

import coder.TM65.daos.CategoryDao;
import coder.TM65.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "cat")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

    @GetMapping(value = "all")
    public String all(Model model) {
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        return "cat/all";
    }

    @GetMapping(value = "create")
    public String create() {
        return "cat/create";
    }

    @PostMapping(value = "create")
    public String makeCat(HttpServletRequest request) {
        String name = request.getParameter("name");
        Category cat = new Category(name);
        categoryDao.save(cat);
        return "redirect:/cat/all";
    }

    @GetMapping(value = "edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        System.out.println("Id is " + id);
        Category category = categoryDao.findById(id).orElse(null);
        model.addAttribute("cat", category);
        return "cat/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String edit(@PathVariable int id, HttpServletRequest request) {
        Category category = categoryDao.findById(id).orElse(null);
        String new_name = request.getParameter("name");
        category.setName(new_name);
        categoryDao.save(category);
        return "redirect:/cat/all";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable int id) {
        Category category = categoryDao.findById(id).orElse(null);
        categoryDao.delete(category);
        return "redirect:/cat/all";
    }

}
