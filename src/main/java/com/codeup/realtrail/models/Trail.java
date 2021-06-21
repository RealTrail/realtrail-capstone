package com.codeup.realtrail.models;

import javax.persistence.*;

@Entity
@Table(name = "trails")
public class Trail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(length = 60)
    private String name;
    @Column
    private float difficultyLevel;
    @Column
    private int rating;
    @Column
    private float length;
    @Column
    private float elevation;
    @Column
    private String type;
    @Column
    private String trailDetails;

    // constructors
    public Trail() {
    }

    public Trail(int id, String name, float difficultyLevel, int rating, float length, float elevation, String type, String trailDetails) {
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trailDetails = trailDetails;
    }

    public Trail(String name, float difficultyLevel, int rating, float length, float elevation, String type, String trailDetails) {
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trailDetails = trailDetails;
    }


    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
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

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
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
}
