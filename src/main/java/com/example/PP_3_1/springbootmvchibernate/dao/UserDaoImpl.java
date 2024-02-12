package com.example.PP_3_1.springbootmvchibernate.dao;


import com.example.PP_3_1.springbootmvchibernate.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> show() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public void delete(int id) {
        User userToDelete = find(id);
        if (userToDelete != null) {
            entityManager.remove(userToDelete);
        }
    }
    @Override
    public void update(int id, User updateUser) {
        if (find(id) != null) {
            entityManager.merge(updateUser);
        }
    }
    @Override
    public User find(int id) {
        return entityManager.find(User.class, id);
    }
}
