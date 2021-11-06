package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

import java.util.Calendar;

@Controller
public class PostControl {
    private final ForumService forumService;

    public PostControl(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        Post post = Post.of("", "");
        model.addAttribute("post", post);
        return "post/edit";
    }

    @GetMapping("/update")
    public String edit(@RequestParam("id") int id, Model model) {
        Post post = forumService.findPostById(id);
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        Post oldPost = forumService.findPostById(post.getId());
        if (oldPost != null) {
            post.setCreated(oldPost.getCreated());
        } else {
            post.setCreated(Calendar.getInstance());
        }
        forumService.save(post);
        return "redirect:/";
    }
}