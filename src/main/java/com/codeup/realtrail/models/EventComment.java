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

    @Column
    private LocalDateTime date;

    @Column(columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn (name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private User owner;

    // constructors
    public EventComment() {
    }

    public EventComment(LocalDateTime date, String content, Event event, User owner) {
        this.date = date;
        this.content = content;
        this.event = event;
        this.owner = owner;
    }

    public EventComment(int id, LocalDateTime date, String content, Event event, User owner) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.event = event;
        this.owner = owner;
    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
