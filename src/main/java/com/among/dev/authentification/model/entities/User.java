package com.among.dev.authentification.model.entities;

import com.among.dev.authentification.utilities.database.interfaces.DBEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "USER_ACCOUNT")
public class User implements DBEntity {

    @GeneratedValue
    int id;

    @Id
    @Column(name = "username", length = 32, unique = true)
    private String username;

    @Id
    @Column(name = "password", length = 32)
    private String password;

    @Column(name = "email", length = 256, unique = true)
    private String email;


    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
}
