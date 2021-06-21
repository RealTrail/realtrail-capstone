package com.codeup.realtrail.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Event#",updatable = false)
    private int id;
    @OneToOne
    @Column(name = "FK user_id")
    private User manager;
    @Column(length = 100)
    private String name;
    @Column(length = 12)
    private LocalDate date;
    @Column(length = 6)
    private LocalTime time;
    @Column(length = 100)
    private String location;
    @OneToOne
    @Column(updatable = false, name = "FK trail_id")
    private Trail trail;
    @Column(length = 150)
    private String meet_location;
    @Column(length = 12)
    private LocalTime meet_time;
    @Column(length = 500)
    private String event_details;


    // constructors
    public Event() {
    }

    public Event(int id, User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meet_location, LocalTime meet_time, String event_details) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.trail = trail;
        this.meet_location = meet_location;
        this.meet_time = meet_time;
        this.event_details = event_details;
    }

    public Event(User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meet_location, LocalTime meet_time, String event_details) {
        this.manager = manager;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.trail = trail;
        this.meet_location = meet_location;
        this.meet_time = meet_time;
        this.event_details = event_details;
    }

    // getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }
    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    public String getMeet_location() {
        return meet_location;
    }
    public void setMeet_location(String meet_location) {
        this.meet_location = meet_location;
    }

    public LocalTime getMeet_time() {
        return meet_time;
    }
    public void setMeet_time(LocalTime meet_time) {
        this.meet_time = meet_time;
    }

    public String getEvent_details() {
        return event_details;
    }
    public void setEvent_details(String event_details) {
        this.event_details = event_details;
    }
}
