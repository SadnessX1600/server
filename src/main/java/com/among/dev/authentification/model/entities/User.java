package com.among.dev.authentification.model.entities;

import com.among.dev.authentification.model.helpers.validators.EmailRegexpValidator;
import com.among.dev.authentification.utilities.database.interfaces.DBEntity;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "USER_ACCOUNT")
//@SequenceGenerator(name="USER_ACCOUNT" , sequenceName="idGenerator")
public class User implements DBEntity {
//    @Column(unique=true)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="idGenerator")
//    int id;

    @Id
    @Column(name = "username", length = 32, unique = true)
    private String username;

    @Id
    @Column(name = "password", length = 32)
    private String password;

    // email is not an id, but should be correct!
    @Column(name = "email", length = 256, unique = true)
    private String email;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // TODO Check that it's correct to create user as user+pass only
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPK() {
        return username;
    }

    public String getPKName() {
        return "username";
    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
