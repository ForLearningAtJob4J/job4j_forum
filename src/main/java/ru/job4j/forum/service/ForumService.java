package ru.job4j.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public ForumService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postRepository.save(
                Post.of("Как убить дракона?",
                        "В последнее время слишком много драконов в городе и окрестностях.")
        );
        this.postRepository.save(
                Post.of("Кто виноват?",
                        "Издавна интересуют людей вопросы: Кто виноват, что делать и подобные.")
        );
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
        return new ArrayList<>(postRepository.findAll());
    }

    public Post findPostById(int id) {
        return postRepository.findById(id);
    }

    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail();
    }

}