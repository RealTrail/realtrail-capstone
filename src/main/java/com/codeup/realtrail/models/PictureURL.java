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

    @ManyToOne
    @JoinColumn (name = "trail_id")
    private Trail trail;

    // constructors
    public PictureURL() {
    }

    public PictureURL(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public PictureURL(String pictureUrl, Trail trail) {
        this.pictureUrl = pictureUrl;
        this.trail = trail;
    }

    public PictureURL(long id, String pictureUrl, Trail trail) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.trail = trail;
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

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }
}


