package com.codeup.realtrail.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "trails")
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String difficultyLevel;

    @Column(nullable = false)
    private float rating;

    @Column(nullable = false)
    private float length;

    @Column(nullable = false)
    private float elevation;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double latitude;

    @Column(columnDefinition="TEXT", nullable = false)
    private String trailDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    @JsonManagedReference(value = "trail-events")
    private List<Event> events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    @JsonManagedReference(value = "trail-trailImages")
    private List<PictureURL> trailImages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    @JsonManagedReference(value = "trail-trailComments")
    private List<TrailComment> trailComments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trail")
    @JsonManagedReference(value = "trail-mapPoints")
    private List<MapPoint> mapPoints;

    // constructors
    public Trail() {
    }

    public Trail(String name, String difficultyLevel, float length, String type, String trailDetails) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.length = length;
        this.type = type;
        this.trailDetails = trailDetails;
    }

    public Trail(String name, String difficultyLevel, float length, String type, String trailDetails, List<PictureURL> trailImages) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.length = length;
        this.type = type;
        this.trailDetails = trailDetails;
        this.trailImages = trailImages;
    }

    public Trail(long id, String name, String difficultyLevel, float rating, float length, float elevation, String type, double longitude, double latitude, String trailDetails, List<Event> events, List<PictureURL> trailImages, List<TrailComment> trailComments, List<MapPoint> mapPoints) {
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.trailDetails = trailDetails;
        this.events = events;
        this.trailImages = trailImages;
        this.trailComments = trailComments;
        this.mapPoints = mapPoints;
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

    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(String difficultyLevel) {
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

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public List<MapPoint> getMapPoints() {
        return mapPoints;
    }
    public void setMapPoints(List<MapPoint> mapPoints) {
        this.mapPoints = mapPoints;
    }
}
