package com.homethings.homethingsboot.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "homes")
public class Home {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, length = 32)
    private String title;

    @OneToMany(mappedBy = "home", fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "home")
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "home")
    private List<Payment> paymentList = new ArrayList<>();

    @OneToMany(mappedBy = "home")
    private List<Note> noteList = new ArrayList<>();

    @ManyToOne
    private Account creator;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date date;

    public Home(String title, Account creator, Date date) {
        this.title = title;
        this.creator = creator;
        this.date = date;
    }

    public Home() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
