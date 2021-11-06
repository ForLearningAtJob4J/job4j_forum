package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(1);

    public UserRepository() {
        save(User.of("Admin", "secret"));
    }

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(id.getAndIncrement());
        }
        users.put(user.getId(), user);
    }

    public void deleteById(int id) {
        users.remove(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(int id) {
        return users.get(id);
    }

    public User findByEmail() {
        return null;
    }
}