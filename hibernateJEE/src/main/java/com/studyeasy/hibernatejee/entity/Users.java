package com.studyeasy.hibernatejee.entity;


import jakarta.persistence.*;


import jakarta.persistence.Column;

@Entity(name = "users")
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "username")
    String userName;

    @Column(name = "password")
    String password;

    @Column(name = "first_name")
    String firstname;

    @Column(name = "last_name")
    String lastname;

    public Users(int userId, String userName, String password, String firstname, String lastname) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Users() {

    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
