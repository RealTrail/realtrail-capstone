package com.codeup.realtrail.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @OneToOne
    private User manager;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(nullable = false, length = 12)
    private LocalDate date;
    
    @Column(nullable = false, length = 6)
    private LocalTime time;
    
    @Column(nullable = false, length = 100)
    private String location;
    
    @ManyToOne
    @JoinColumn (name = "trail_id")
    private Trail trail;
    
    @Column(nullable = false, length = 150)
    private String meetLocation;
    
    @Column(nullable = false, length = 12)
    private LocalTime meetTime;
    
    @Column(columnDefinition="TEXT")
    private String eventDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<TrailComment> trailComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<PictureURL> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="events_participants",
            joinColumns = {@JoinColumn(name="event_id")},
            inverseJoinColumns = {@JoinColumn(name="participant_id")}
    )
    private List<User> participants;

    // constructors
    public Event() {
    }

    public Event(User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails, List<TrailComment> trailComments, List<PictureURL> images, List<User> participants) {
        this.manager = manager;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
        this.trailComments = trailComments;
        this.images = images;
        this.participants = participants;
    }

    public Event(long id, User manager, String name, LocalDate date, LocalTime time, String location, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails, List<TrailComment> trailComments, List<PictureURL> images, List<User> participants) {
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
        this.trailComments = trailComments;
        this.images = images;
        this.participants = participants;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public List<TrailComment> getTrailComments() {
        return trailComments;
    }

    public void setTrailComments(List<TrailComment> trailComments) {
        this.trailComments = trailComments;
    }

    public List<PictureURL> getImages() {
        return images;
    }

    public void setImages(List<PictureURL> images) {
        this.images = images;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
