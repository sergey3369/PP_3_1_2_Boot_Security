package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT e FROM User e")
                .getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(Long id) {
        return (User) entityManager.
                createQuery("select u from User u where u.id = :id")
                .setParameter("id",  id)
                .getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery("delete from User u where u.id = :id")
                .setParameter("id",  id)
                .executeUpdate();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUsername(String username) {
        return (User) entityManager.createQuery("select u from User u where u.name = :name")
                .setParameter("name", username)
                .getSingleResult();
    }
}
