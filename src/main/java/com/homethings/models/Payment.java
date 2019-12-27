package com.homethings.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private double amount;

    @ManyToOne(optional = false)
    private Account sender;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Home home;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date date;

    public Payment(double amount, Account sender, Date date) {
        this.amount = amount;
        this.sender = sender;
        this.date = date;
    }

    public Payment() {

    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public Account getSender() {
        return sender;
    }

    public void setSender(Account performed) {
        this.sender = performed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

}
