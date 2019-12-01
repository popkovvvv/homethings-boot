package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HomeDAO {
    @PersistenceContext
    private  EntityManager manager;

    public HomeDAO() {
    }

    public HomeDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Home home) {
        manager.persist(home);
    }

    public Home findById(long id){
        Home foundHome = manager.find(Home.class,id);
        if (foundHome == null){
            throw new EntityNotFoundException(
                    "Can't find a Note for ID " + id
            );
        }
        return foundHome;
    }

    public void delete(long id) {
        Home home = manager.find(Home.class, id);
        if (home == null) throw new EntityNotFoundException("home with id " + id + " is not found");
        manager.remove(home);
    }

    public Home findByTitle(String title) {
        return manager.createQuery("FROM Home where title = :p", Home.class)
                .setParameter("p", title)
                .getSingleResult();
    }

    @Transactional
    public void addMember(long homeID, long userID) {
        Home home = manager.find(Home.class, homeID);
        User user = manager.find(User.class, userID);

        if (home == null) throw new EntityNotFoundException("Дом с ID: " + homeID + " не найден");
        if (user == null) throw new EntityNotFoundException("Такого пользавателя не существует!");

        home.getUsers().add(user);
        manager.merge(home);
        System.err.println(home);

    }

    @Transactional
    public void deleteMember(long homeID, long userID) {
        Home home = manager.find(Home.class, homeID);
        User user = manager.find(User.class, userID);

        if (home == null) throw new EntityNotFoundException("Дом с ID: " + homeID + " не найден");
        if (user == null) throw new EntityNotFoundException("Такого пользавателя не существует!");

        home.getUsers().remove(user);
        manager.merge(home);

    }

    @Transactional
    public void deleteTask(long homeID, long taskId) {
        Home home = manager.find(Home.class, homeID);
        Task task = manager.find(Task.class, taskId);

        if (home == null) throw new EntityNotFoundException("Дом с ID: " + homeID + " не найден");
        if (task == null) throw new EntityNotFoundException("Такой задачи не существует!");

        home.getTaskList().remove(task);
        manager.merge(home);

    }

    public void addTask(long id, Task task) {
        Home home = manager.find(Home.class, id);
        if (home == null) throw new EntityNotFoundException("home with id " + id + " is not found");

        home.getTaskList().add(task);
        manager.merge(home);
    }

    public void addPayment(long id, Payment payment) {
        Home home = manager.find(Home.class, id);
        if (home == null) throw new EntityNotFoundException("home with id " + id + " is not found");
        home.getPaymentList().add(payment);
        manager.merge(home);
    }

    public void addNote(long id, Note note) {
        Home home = manager.find(Home.class, id);
        if (home == null) throw new EntityNotFoundException("home with id " + id + " is not found");
        home.getNoteList().add(note);
        manager.merge(home);
    }

    public List<Task> getTaskList(long id) {
        Home home = manager.find(Home.class,id);
        return home.getTaskList();
    }

    public List<User> getUserList(long id) {
        Home home = manager.find(Home.class,id);
        return home.getUsers();
    }

    public List<Payment> getPaymentsList(long id) {
        Home home = manager.find(Home.class,id);
        return home.getPaymentList();
    }

    public List<Note> getNoteList(long id) {
        Home home = manager.find(Home.class,id);
        return home.getNoteList();
    }



}
