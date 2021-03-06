package com.codeup.realtrail.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "interests")
public class UserInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String interest;

    @ManyToMany(mappedBy = "interests")
    private List<User> users;

    // constructors
    public UserInterest() {
    }

    public UserInterest(String interest, List<User> users) {
        this.interest = interest;
        this.users = users;
    }

    public UserInterest(long id, String interest, List<User> users) {
        this.id = id;
        this.interest = interest;
        this.users = users;
    }

    // getters and setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
