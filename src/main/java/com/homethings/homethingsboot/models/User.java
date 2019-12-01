package com.homethings.homethingsboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 32, unique = true)
    private String login;

    @Column(length = 32, nullable = false)
    private String password;

    @Column()
    private AccessRole role;

    @Email()
    @Column(length = 32, unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Home home;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date date;


    @OneToOne
    private Profile profile;

    public enum AccessRole {ADMIN,USER}

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(AccessRole role) {
        this.role = role;
    }

    public AccessRole getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }




}

