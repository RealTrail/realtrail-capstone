package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = "FK event_id")
    private Event event;
    @Column
    private LocalTime time;
    @Column
    private String msg_content;
    @OneToOne
    @Column(updatable = false, name = "FK user_id")
    User sender;



    // constructors
    public Message() {
    }

    public Message(int id, Event event, LocalTime time, String msg_content, User sender) {
        this.id = id;
        this.event = event;
        this.time = time;
        this.msg_content = msg_content;
        this.sender = sender;
    }

    public Message(Event event, LocalTime time, String msg_content, User sender) {
        this.event = event;
        this.time = time;
        this.msg_content = msg_content;
        this.sender = sender;
    }



    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getMsg_content() {
        return msg_content;
    }
    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
}
