package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.ForumService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Controller
public class IndexControl {
    private final ForumService service;

    public IndexControl(ForumService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, ServletRequest request, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        model.addAttribute("posts", service.findAllPosts());
        return "index";
    }
}