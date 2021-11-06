package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepository {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(1);

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(ids.getAndIncrement());
        }
        posts.put(post.getId(), post);
    }

    public void deleteById(int id) {
        posts.remove(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}