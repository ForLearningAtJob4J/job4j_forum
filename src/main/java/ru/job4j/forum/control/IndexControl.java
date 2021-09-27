package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Controller
public class IndexControl {
    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, ServletRequest request, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        model.addAttribute("posts", posts.getAll());
        return "index";
    }
}