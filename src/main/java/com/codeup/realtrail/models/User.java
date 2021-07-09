package com.codeup.realtrail.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(length = 12)
    private String phoneNumber;

    @Column(length = 30)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(updatable = false)
    private String gender;

    @Column
    private String profileImageUrl;

    @Column(columnDefinition = "boolean default false")
    private boolean isAdmin;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="users_interests",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="interest_id")}
    )
    private List<UserInterest> interests;

    @ManyToMany(mappedBy = "participants")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference(value = "owner-trailComments")
    private List<TrailComment> trailComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference(value = "owner-eventComments")
    private List<EventComment> eventComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonManagedReference(value = "owner-createdEvents")
    private List<Event> createdEvents;


    // Constructors
    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(User copy) {
        this.id = copy.id;
        this.username = copy.username;
        this.email = copy.email;
        this.password = copy.password;
    }

    public User(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public User(String firstName, String lastName, String username, String email, String password, String phoneNumber, String city, String state, String gender, String profileImageUrl, boolean isAdmin, List<UserInterest> interests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.state = state;
        this.gender = gender;
        this.profileImageUrl = profileImageUrl;
        this.isAdmin = isAdmin;
        this.interests = interests;
    }

    public User(long id, String firstName, String lastName, String username, String email, String password, String phoneNumber, String city, String state, String gender, String profileImageUrl, boolean isAdmin, List<UserInterest> interests, List<Event> events, List<TrailComment> trailComments, List<EventComment> eventComments, List<Event> createdEvents) {
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
        this.profileImageUrl = profileImageUrl;
        this.isAdmin = isAdmin;
        this.interests = interests;
        this.events = events;
        this.trailComments = trailComments;
        this.eventComments = eventComments;
        this.createdEvents = createdEvents;
    }

    // getter and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<UserInterest> getInterests() {
        return interests;
    }
    public void setInterests(List<UserInterest> interests) {
        this.interests = interests;
    }

    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<TrailComment> getTrailComments() {
        return trailComments;
    }
    public void setTrailComments(List<TrailComment> trailComments) {
        this.trailComments = trailComments;
    }

    public List<EventComment> getEventComments() {
        return eventComments;
    }
    public void setEventComments(List<EventComment> eventComments) {
        this.eventComments = eventComments;
    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }
    public void setCreatedEvents(List<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }

}
