package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_comments")
public class EventComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = "FK event_id")
    private Event event_id;
    @OneToOne
    @Column(updatable = false, name = ("FK user_id"))
    private User user_id;
    @Column
    private LocalDateTime date;
    @Column(length = 500)
    private String content;


    // constructors
    public EventComment() {
    }

    public EventComment(int id, Event event_id, User user_id, LocalDateTime date, String content) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.date = date;
        this.content = content;
    }

    public EventComment(Event event_id, User user_id, LocalDateTime date, String content) {
        this.event_id = event_id;
        this.user_id = user_id;
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

    public Event getEvent_id() {
        return event_id;
    }
    public void setEvent_id(Event event_id) {
        this.event_id = event_id;
    }

    public User getUser_id() {
        return user_id;
    }
    public void setUser_id(User user_id) {
        this.user_id = user_id;
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
