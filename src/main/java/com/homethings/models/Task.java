package com.homethings.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column
    private boolean checked;

    @ManyToOne(optional = false)
    private Account sender;

    @ManyToOne(optional = false)
    private Account performed;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Home home;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date date;

    public Task() {
    }

    public Task(String title, Account sender, Account performed) {
        this.title = title;
        this.sender = sender;
        this.performed = performed;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSender(Account provider) {
        this.sender = provider;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Account getSender() {
        return sender;
    }

    public Date getDate(Date date) {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getPerformed() {
        return performed;
    }

    public void setPerformed(Account performed) {
        this.performed = performed;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public String toString() {
        return "Task [header: " + title + ", checked: " + checked
                + ", provider: " + sender.toString() + ", performed:" + performed +" ]";
    }


}

