package com.homethings.homethingsboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "user")
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 32, unique = true)
    private String login;

    @Column(length = 64, nullable = false)
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

    public Account() {
    }

    public Account(String login, String password,String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }





}

