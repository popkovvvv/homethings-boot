package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NoteDAO {
    @PersistenceContext
    private  EntityManager manager;

    public NoteDAO(){}

    public NoteDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Note note) {
        manager.persist(note);
    }

    @Transactional
    public void delete(long id) {
        Note note = manager.find(Note.class, id);
        if (note == null) throw new EntityNotFoundException("note with id " + id + " is not found");
        manager.remove(note);
    }

    public Note findById(long id){
        Note foundNote = manager.find(Note.class,id);
        if (foundNote == null){
            throw new EntityNotFoundException(
                    "Can't find a Note for ID " + id
            );
        }
        return foundNote;
    }

    public List<Note> noteList() {
        return manager.createQuery("from Note ", Note.class)
                .getResultList();
    }

    public List<Note> findByProvider(User provider) {
        return manager.createQuery("FROM Note where author = :p", Note.class)
                .setParameter("p", provider)
                .getResultList();
    }

    public void update(Note note){
        manager.merge(note);
    }
}
