package com.homethings.homethingsboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Home home;

    public Note(String title, String text, User author, Date date) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    public Note() { }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String heading) {
        this.title = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User provider) {
        this.author = provider;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
