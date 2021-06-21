package com.codeup.realtrail.models;

import javax.persistence.*;

@Entity
@Table(name = "picture_urls")
public class PictureURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String pictureUrl;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn (name = "trail_id")
    private Trail trail;

    // constructors
    public PictureURL() {
    }

    public PictureURL(String pictureUrl, String description, Trail trail) {
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.trail = trail;
    }

    public PictureURL(int id, String pictureUrl, String description, Trail trail) {
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.trail = trail;
    }

    // getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
}


