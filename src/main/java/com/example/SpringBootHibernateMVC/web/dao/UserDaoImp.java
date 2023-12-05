package com.example.SpringBootHibernateMVC.web.dao;

import org.springframework.stereotype.Repository;
import com.example.SpringBootHibernateMVC.web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void add(User user) {
        entityManager.persist(user);
    }

    public void update(User user) {
        User entity = findById(user.getId());

        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
    }

    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    public void delete(Long id) {
        entityManager.createQuery("delete from User u where u.id=:id")
                .setParameter("id", id).executeUpdate();
    }

    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}