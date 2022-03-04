package ru.kata.spring.boot_security.demo.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    final private PasswordEncoder passwordEncoder;
    final private UserDao dao;

    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder, UserDao dao) {
        this.passwordEncoder = passwordEncoder;
        this.dao = dao;
    }

    @Override
    public List<User> getUsers() {
        return dao.getUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
    }

    @Override
    public User getUser(Long id) {
        return dao.getUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        dao.deleteUser(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        if (!user.getPassword().equals(dao
                .getUser(user.getId())
                .getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        dao.update(user);
    }

    @Override
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByUsername(username);
    }
}
