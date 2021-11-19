package ru.job4j.forum.control;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ForumService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final ForumService forumService;

    public RegControl(PasswordEncoder encoder, ForumService forumService) {
        this.encoder = encoder;
        this.forumService = forumService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(forumService.findByAuthority("ROLE_USER"));
        String path;

        try {
            forumService.save(user);
            path = "redirect:/login";
        } catch (DataIntegrityViolationException dive) {
            path = "redirect:/reg?error=true";
        }
        return path;
    }

    @GetMapping("/reg")
    public String reg(Model model,
                      @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "This username is already exists! Choose another.";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}