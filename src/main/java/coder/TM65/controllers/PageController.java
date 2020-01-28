package coder.TM65.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("title","Bible");
        return "index";
    }
    @RequestMapping(value = "/",method= RequestMethod.POST)
    public String postIndex(HttpServletRequest request) {
       String email = request.getParameter("email");
       String pass = request.getParameter("password");
        System.out.format("Email is %s and password is %s",email,pass);
       return "redirect:/";
    }

    @GetMapping(value="about")
    public String about(){
        return "about";
    }



}
