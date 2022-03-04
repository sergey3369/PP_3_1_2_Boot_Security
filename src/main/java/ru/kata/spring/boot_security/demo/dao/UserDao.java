package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void saveUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    void update(User user);

    User findByUsername(String username);
}
