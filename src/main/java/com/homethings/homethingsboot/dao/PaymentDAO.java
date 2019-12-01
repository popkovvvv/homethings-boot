package com.homethings.homethingsboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.homethings.homethingsboot.models.*;


import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PaymentDAO {
    @PersistenceContext
    private  EntityManager manager;

    public  PaymentDAO(){}

    public PaymentDAO(EntityManager manager) {
        this.manager = manager;
    }

    @Transactional
    public void create(Payment payment) {
        manager.persist(payment);
    }

    @Transactional
    public void delete(long id) {
        Payment payment = manager.find(Payment.class, id);
        if (payment == null) throw new EntityNotFoundException("payment with id " + id + " is not found");
        manager.remove(payment);
    }

    public Payment findById(long id){
        Payment foundPayment = manager.find(Payment.class,id);
        if (foundPayment == null){
            throw new EntityNotFoundException(
                    "Can't find a Payment for ID " + id
            );
        }
        return foundPayment;
    }

    public List<Payment> paymentList() {
        return manager.createQuery("from Payment ", Payment.class)
                .getResultList();
    }

    public List<Payment> findByPerformed(User performed) {
        return manager.createQuery("FROM Payment where sender = :p", Payment.class)
                .setParameter("p", performed)
                .getResultList();
    }

    public Payment findByAmount(Payment amount) {
        return manager.createQuery("FROM Payment where amount = :p", Payment.class)
                .setParameter("p", amount.getAmount())
                .getSingleResult();
    }

    @Transactional
    public void update(Payment payment){
        manager.merge(payment);
    }


}
