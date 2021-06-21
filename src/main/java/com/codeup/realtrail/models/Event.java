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
    @Column(name = "FK userId")
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
    @Column(updatable = false, name = "FK trailId")
    private Trail trail;
    @Column(length = 150)
    private String meetLocation;
    @Column(length = 12)
    private LocalTime meetTime;
    @Column(length = 500)
    private String eventDetails;


    // constructors
    public Event() {
    }

    public Event(int id, User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meet_location, LocalTime meetTime, String eventDetails) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
    }

    public Event(User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails) {
        this.manager = manager;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
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

    public String getMeetLocation() {
        return meetLocation;
    }
    public void setMeetLocation(String meetLocation) {
        this.meetLocation = meetLocation;
    }

    public LocalTime getMeetTime() {
        return meetTime;
    }
    public void setMeetTime(LocalTime meetTime) {
        this.meetTime = meetTime;
    }

    public String getEventDetails() {
        return eventDetails;
    }
    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
