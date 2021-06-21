package com.codeup.realtrail.models;


import com.codeup.realtrail.util.Password;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(nullable = false, length = 50)
    private String first_name;
    @Column(nullable = false, length = 50)
    private String last_name;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(length = 10)
    private String phone_number;
    @Column(length = 30)
    private String city;
    @Column(length = 2)
    private String state;
    @Column(updatable = false)
    private boolean is_admin;

    // Constructors
    public User() {
    }

    public User(int id, String first_name, String last_name, String username, String email, String password, String phone_number, String city, String state, boolean is_admin) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.city = city;
        this.state = state;
        this.is_admin = is_admin;
    }

    public User(String first_name, String last_name, String username, String email, String password, String phone_number, String city, String state, boolean is_admin) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.city = city;
        this.state = state;
        this.is_admin = is_admin;
    }


    // getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = Password.hash(password);
    }

    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public boolean isIs_admin() {
        return is_admin;
    }
    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }
}
