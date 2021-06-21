package com.codeup.realtrail.models;

import javax.persistence.*;

@Entity
@Table(name = "picture_urls")
public class PictureURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String pictureUrl;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn (name = "trail_id")
    private Trail trail;

    @ManyToOne
    @JoinColumn (name = "event_id")
    private Event event;

    // constructors
    public PictureURL() {
    }

    public PictureURL(String pictureUrl, String description, Trail trail, Event event) {
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.trail = trail;
        this.event = event;
    }

    public PictureURL(long id, String pictureUrl, String description, Trail trail, Event event) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.trail = trail;
        this.event = event;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}


