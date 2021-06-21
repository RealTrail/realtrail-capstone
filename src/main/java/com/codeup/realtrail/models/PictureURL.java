package com.codeup.realtrail.models;

import javax.persistence.*;

@Entity
@Table(name = "picture_urls")
public class PictureURL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(length = 150)
    private String url;
    @Column
    private String description;
    @OneToOne
    @Column(updatable = false, name = "FK trail_id")
    private Trail trail;


    // constructors
    public PictureURL() {
    }

    public PictureURL(int id, String url, String description, Trail trail) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.trail = trail;
    }

    public PictureURL(String url, String description, Trail trail) {
        this.url = url;
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

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
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


