package com.homethings.homethingsboot.models.pojo;

import com.homethings.homethingsboot.models.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePojo {

    private long id;

    private String title;

    private List<User> users = new ArrayList<>();

    private List<Task> taskList = new ArrayList<>();

    private List<Payment> paymentList = new ArrayList<>();

    private List<Note> noteList = new ArrayList<>();

    private User creator;

    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
