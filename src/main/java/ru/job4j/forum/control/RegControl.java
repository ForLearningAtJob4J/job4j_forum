package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.UserRepository;

@Controller
public class RegControl {

    private final UserRepository users;

    public RegControl(UserRepository users) {
        this.users = users;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(user.getPassword());
        String path;

        try {
            users.save(user);
            path = "redirect:/login";
        } catch (Exception e) {
            path = "redirect:/reg?error=true";
        }
        return path;
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute Post post,
                      Model model,
                      @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "This username is already exists! Choose another.";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}