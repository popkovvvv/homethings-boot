package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {
    @PersistenceContext
    private  EntityManager manager;

    public UserDAO(){ }

    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(User user) {
        manager.persist(user);
    }

    @Transactional
    public void delete(long id) {
        User user = manager.find(User.class, id);
        if (user == null) throw new EntityNotFoundException("User with id " + id + " is not found");
        manager.remove(user);

    }

    public User findById(long id){
        User foundUser = manager.find(User.class,id);
        if (foundUser == null){
            throw new EntityNotFoundException(
                    "Can't find a User for ID " + id
            );
        }
        return foundUser;
    }

    public User findByEmail(String email) {
        return manager.createQuery("FROM User where email = :p", User.class)
                .setParameter("p", email)
                .getSingleResult();
    }

    public List<User> findByRole(String role) {
        return manager.createQuery("FROM User where role = :p", User.class)
                .setParameter("p", role)
                .getResultList();
    }

    public User findByLoginAndPassword(String email, String password) {
        return manager.createQuery(
                "from User where email = :email AND password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    public List<User> listUsers() {
        return manager.createQuery("from User", User.class)
                .getResultList();
    }

    @Transactional
    public void update(User user){
        manager.merge(user);
    }






}
