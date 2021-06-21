package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventComments")
public class EventComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = "FK eventId")
    private Event eventId;
    @OneToOne
    @Column(updatable = false, name = ("FK userId"))
    private User userId;
    @Column
    private LocalDateTime date;
    @Column(length = 500)
    private String content;


    // constructors
    public EventComment() {
    }

    public EventComment(int id, Event eventId, User userId, LocalDateTime date, String content) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.date = date;
        this.content = content;
    }

    public EventComment(Event eventId, User userId, LocalDateTime date, String content) {
        this.eventId = eventId;
        this.userId = userId;
        this.date = date;
        this.content = content;
    }


    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Event getEventId() {
        return eventId;
    }
    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
