package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trailComments")
public class TrailComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @OneToOne
    @Column(updatable = false, name = ("FK userId"))
    private User userId;
    @OneToOne
    @Column(updatable = false, name = "FK trailId")
    private Trail trailId;
    @Column
    private LocalDateTime date;
    @Column(length = 500)
    private String content;

    // constructors

    public TrailComment() {
    }

    public TrailComment(int id, User userId, Trail trailId, LocalDateTime date, String content) {
        this.id = id;
        this.userId = userId;
        this.trailId = trailId;
        this.date = date;
        this.content = content;
    }

    public TrailComment(User userId, Trail trailId, LocalDateTime date, String content) {
        this.userId = userId;
        this.trailId = trailId;
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

    public User getUserId() {
        return userId;
    }
    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Trail getTrailId() {
        return trailId;
    }
    public void setTrailId(Trail trailId) {
        this.trailId = trailId;
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
