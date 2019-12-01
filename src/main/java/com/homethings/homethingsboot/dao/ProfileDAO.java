package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProfileDAO {

    @PersistenceContext
    private EntityManager manager;

    public ProfileDAO(){ }

    public ProfileDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Profile user) {
        manager.persist(user);
    }

    @Transactional
    public void delete(long id) {
        Profile profile = manager.find(Profile.class, id);
        if (profile == null) throw new EntityNotFoundException("Profile with id " + id + " is not found");
        manager.remove(profile);
    }

    @Transactional
    public void update(Profile profile){
        manager.merge(profile);
    }

    public List<Profile> profileList() {
        return manager.createQuery("from Profile ", Profile.class)
                .getResultList();
    }

    public Profile findById(long id){
        Profile foundProfile = manager.find(Profile.class,id);
        if (foundProfile == null){
            throw new EntityNotFoundException(
                    "Can't find a Profile for ID " + id
            );
        }
        return foundProfile;
    }

    public User findByUser(User user){
        return manager.createQuery("from Profile where user = :p", User.class)
                .setParameter("p", user)
                .getSingleResult();

    }


}
