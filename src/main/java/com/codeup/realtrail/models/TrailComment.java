package com.codeup.realtrail.models;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trail_comments")
public class TrailComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDateTime date;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, columnDefinition="TEXT")
    private String content;

    @ManyToOne
    @JoinColumn (name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn (name = "trail_id")
    private Trail trail;

    // constructors

    public TrailComment() {
    }

    public TrailComment(LocalDateTime date, int rating, String content, User owner, Trail trail) {
        this.date = date;
        this.rating = rating;
        this.content = content;
        this.owner = owner;
        this.trail = trail;
    }

    public TrailComment(long id, LocalDateTime date, int rating, String content, User owner, Trail trail) {
        this.id = id;
        this.date = date;
        this.rating = rating;
        this.content = content;
        this.owner = owner;
        this.trail = trail;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }
}
