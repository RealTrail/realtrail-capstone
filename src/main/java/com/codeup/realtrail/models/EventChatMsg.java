package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "eventChat")
public class EventChatMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = "FK eventId")
    private Event event;
    @Column
    private LocalTime time;
    @Column
    private String msgContent;
    @OneToOne
    @Column(updatable = false, name = "FK userId")
    User senderId;



    // constructors
    public EventChatMsg() {
    }

    public EventChatMsg(int id, Event event, LocalTime time, String msgContent, User senderId) {
        this.id = id;
        this.event = event;
        this.time = time;
        this.msgContent = msgContent;
        this.senderId = senderId;
    }

    public EventChatMsg(Event event, LocalTime time, String msgContent, User senderId) {
        this.event = event;
        this.time = time;
        this.msgContent = msgContent;
        this.senderId = senderId;
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

    public String getMsgContent() {
        return msgContent;
    }
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public User getSenderId() {
        return senderId;
    }
    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }
}
