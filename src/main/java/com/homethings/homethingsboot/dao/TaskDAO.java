package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskDAO {
    @PersistenceContext
    private  EntityManager manager;

    public TaskDAO(){}

    public TaskDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Task task, long id) {
        try {
            Home homeFound = manager.find(Home.class,id);
            task.setHome(homeFound);
        } catch (Exception e) {
            throw new EntityNotFoundException("Home not found: " + id);
        }
        manager.persist(task);
    }

    @Transactional
    public void delete(long id) {
        Task task = manager.find(Task.class, id);
        if (task == null) throw new EntityNotFoundException("Task with id " + id + " is not found");
        manager.remove(task);
    }

    public Task findById(long id){
        Task foundTask = manager.find(Task.class,id);
        if (foundTask == null){
            throw new EntityNotFoundException(
                    "Can't find a Task for ID " + id
            );
        }
        return foundTask;
    }

    public List<Task> findByProvider(User provider) {
        return manager.createQuery("FROM Task where sender = :p", Task.class)
                .setParameter("p", provider)
                .getResultList();
    }

    public Task findByPerformed(User performed) {
        return manager.createQuery("FROM Task where performed = :p", Task.class)
                .setParameter("p", performed)
                .getSingleResult();
    }

    public Task findByPerformedOrProvider(User user) {
        return manager.createQuery("FROM Task where performed = :p or sender = :p ", Task.class)
                .setParameter("p", user)
                .getSingleResult();
    }

    public List<Task> listTasks() {
        return manager.createQuery("from Task ", Task.class)
                .getResultList();
    }

    @Transactional
    public void update(Task task){
        manager.merge(task);
    }

}
