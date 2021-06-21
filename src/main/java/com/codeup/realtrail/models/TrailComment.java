package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trail_comments")
public class TrailComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = ("FK user_id"))
    private User user_id;
    @OneToOne
    @Column(updatable = false, name = "FK trail_id")
    private Trail trail_id;
    @Column
    private LocalDateTime date;
    @Column(length = 500)
    private String content;

    // constructors

    public TrailComment() {
    }

    public TrailComment(int id, User user_id, Trail trail_id, LocalDateTime date, String content) {
        this.id = id;
        this.user_id = user_id;
        this.trail_id = trail_id;
        this.date = date;
        this.content = content;
    }

    public TrailComment(User user_id, Trail trail_id, LocalDateTime date, String content) {
        this.user_id = user_id;
        this.trail_id = trail_id;
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

    public User getUser_id() {
        return user_id;
    }
    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Trail getTrail_id() {
        return trail_id;
    }
    public void setTrail_id(Trail trail_id) {
        this.trail_id = trail_id;
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
