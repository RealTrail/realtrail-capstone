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
    private String difficulty_level;
    @Column
    private int rating;
    @Column
    private float length;
    @Column
    private float elevation;
    @Column
    private String type;
    @Column
    private String trail_details;

    // constructors
    public Trail() {
    }

    public Trail(int id, String name, String difficulty_level, int rating, float length, float elevation, String type, String trail_details) {
        this.id = id;
        this.name = name;
        this.difficulty_level = difficulty_level;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trail_details = trail_details;
    }

    public Trail(String name, String difficulty_level, int rating, float length, float elevation, String type, String trail_details) {
        this.name = name;
        this.difficulty_level = difficulty_level;
        this.rating = rating;
        this.length = length;
        this.elevation = elevation;
        this.type = type;
        this.trail_details = trail_details;
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

    public String getDifficulty_level() {
        return difficulty_level;
    }
    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
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

    public String getTrail_details() {
        return trail_details;
    }
    public void setTrail_details(String trail_details) {
        this.trail_details = trail_details;
    }
}
