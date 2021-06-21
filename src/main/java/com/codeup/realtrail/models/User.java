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
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 50)
    private String password;
    @Column(length = 10)
    private String phoneNumber;
    @Column(length = 30)
    private String city;
    @Column(length = 2)
    private String state;
    @Column(updatable = false)
    private String gender;
    @Column(updatable = false)
    private boolean isAdmin;


    // Constructors
    public User() {
    }

    public User(int id, String firstName, String lastName, String username, String email, String password, String phoneNumber, String city, String state, String gender, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.gender = gender;
        this.isAdmin = isAdmin;
    }

    public User(String firstName, String lastName, String username, String email, String password, String phoneNumber, String city, String state, String gender, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.gender = gender;
        this.isAdmin = isAdmin;
    }


    // getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return firstName;
    }
    public void setFirst_name(String first_name) {
        this.firstName = first_name;
    }

    public String getLast_name() {
        return lastName;
    }
    public void setLast_name(String last_name) {
        this.lastName = last_name;
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
        return phoneNumber;
    }
    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
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

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isIs_admin() {
        return isAdmin;
    }
    public void setIs_admin(boolean is_admin) {
        this.isAdmin = is_admin;
    }
}
