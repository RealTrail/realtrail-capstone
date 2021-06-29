package com.codeup.realtrail.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "trails")
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String name;

    @Column(nullable = false)
    private float difficultyLevel;

    @Column
    private float rating;

    @Column(nullable = false)
    private float length;

    @Column
    private float elevation;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition="TEXT")
    private String trailDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    private List<PictureURL> trailImages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    private List<TrailComment> trailComments;

    // constructors
    public Trail() {
    }

    public Trail(String name, float difficultyLevel, float rating, float length, float elevation, String type, String trailDetails, List<Event> events, List<PictureURL> trailImages, List<TrailComment> trailComments) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trailDetails = trailDetails;
        this.events = events;
        this.trailImages = trailImages;
        this.trailComments = trailComments;
    }

    public Trail(long id, String name, float difficultyLevel, float rating, float length, float elevation, String type, String trailDetails, List<Event> events, List<PictureURL> trailImages, List<TrailComment> trailComments) {
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trailDetails = trailDetails;
        this.events = events;
        this.trailImages = trailImages;
        this.trailComments = trailComments;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public float getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(float difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }

    public float getElevation() {
        return elevation;
    }
    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getTrailDetails() {
        return trailDetails;
    }
    public void setTrailDetails(String trailDetails) {
        this.trailDetails = trailDetails;
    }

    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<PictureURL> getTrailImages() {
        return trailImages;
    }
    public void setTrailImages(List<PictureURL> trailImages) {
        this.trailImages = trailImages;
    }

    public List<TrailComment> getTrailComments() {
        return trailComments;
    }
    public void setTrailComments(List<TrailComment> trailComments) {
        this.trailComments = trailComments;
    }
}
