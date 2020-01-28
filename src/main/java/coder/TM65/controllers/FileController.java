package coder.TM65.controllers;

import coder.TM65.models.User;
import coder.TM65.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    private static String UPLOADED_FOLDER = "/Users/waiferkolar/Documents/JavaEE/Projects/TM-65/src/main/resources/static/imgs/uploads//";

    @GetMapping("/upload")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "file/upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        saveImage(user.getFiles(), request, redirectAttributes);
        return "redirect:/cat/all";
    }


    public String saveImage(List<MultipartFile> files, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String UPLOADED_FOLDER = "/Users/waiferkolar/Documents/JavaEE/Projects/TM-65/src/main/resources/static/imgs/uploads//";
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "";

    }
}

