package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public ForumService(PostRepository postRepository, UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    public List<Post> findAllPosts() {
        return new ArrayList<>((ArrayList<Post>) postRepository.findAll());
    }

    public Post findPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public Authority findByAuthority(String userRole) {
        return authorityRepository.findByAuthority(userRole);
    }
}