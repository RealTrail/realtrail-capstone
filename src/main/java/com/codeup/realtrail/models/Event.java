package com.codeup.realtrail.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100)
    @NotBlank(message = "Events must have a name")
    @Size(min = 6, message = "A name must be at least 6 characters.")
    private String name;

    @Column(length = 12)
//    @NotBlank(message = "Events must have a date")
    private LocalDate date;

    @Column(length = 6)
    @DateTimeFormat(pattern = "HH:mm a")
//    @NotBlank(message = "Events must have a time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    @JsonBackReference(value = "owner-createdEvents")
    private User owner;

    @ManyToOne
    @JoinColumn (name = "trail_id")
    @JsonBackReference(value = "trail-events")
    private Trail trail;

    @Column(length = 150)
//    @NotBlank(message = "Events must have a meet location")
    private String meetLocation;

    @Column(length = 12)
    @DateTimeFormat(pattern = "HH:mm a")
//    @NotBlank(message = "Events must have a meet time")
    private LocalTime meetTime;

    @Column(columnDefinition="TEXT")
    private String eventDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @JsonManagedReference(value = "event-eventComments")
    private List<EventComment> eventComments;

    @JsonBackReference
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

    public Event(String name, Trail trail, String meetLocation, String eventDetails) {
        this.name = name;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.eventDetails = eventDetails;
    }

    public Event(String name, LocalDate date, LocalTime time, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
    }

    public Event(String name, LocalDate date, LocalTime time, User owner, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails, List<EventComment> eventComments, List<User> participants) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.owner = owner;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
        this.eventComments = eventComments;
        this.participants = participants;
    }

    public Event(long id, String name, LocalDate date, LocalTime time, User owner, Trail trail, String meetLocation, LocalTime meetTime, String eventDetails, List<EventComment> eventComments, List<User> participants) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.owner = owner;
        this.trail = trail;
        this.meetLocation = meetLocation;
        this.meetTime = meetTime;
        this.eventDetails = eventDetails;
        this.eventComments = eventComments;
        this.participants = participants;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
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

    public List<EventComment> getEventComments() {
        return eventComments;
    }
    public void setEventComments(List<EventComment> eventComments) {
        this.eventComments = eventComments;
    }

    public List<User> getParticipants() {
        return participants;
    }
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
