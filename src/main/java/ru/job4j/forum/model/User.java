package ru.job4j.forum.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class User {
    private int id;

    private String username;

    private String passwordHash;

    private boolean enabled;

    public static User of(String username, String password) {
        User user = new User();
        user.username = username;
        user.passwordHash = makeMD5(password);
        user.enabled = true;
        return user;
    }

    private static String makeMD5(String password) {
        String ret = "ERRORERRORERROR";
        try {
            ret = Arrays.toString(
                    MessageDigest.getInstance("MD5").digest(password.getBytes(StandardCharsets.UTF_8))
            );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setEnabled(boolean b) {
        this.enabled = b;
    }
}