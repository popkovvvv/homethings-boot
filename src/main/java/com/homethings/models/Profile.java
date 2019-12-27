package com.homethings.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String surname;

    @Column()
    private Date birthday;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToOne(mappedBy = "profile")
    private Account account;

    public Profile(){}

    public Profile(String name, String surname, Date birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
