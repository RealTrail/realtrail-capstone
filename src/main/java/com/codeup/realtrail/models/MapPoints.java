package com.codeup.realtrail.models;


import javax.persistence.*;

@Entity
@Table(name = "mapPoints")
public class MapPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private float longitude;
    @Column
    private float latitude;
    @Column
    private String mapDetails;
    @OneToOne
    @Column(updatable = false, name = "FK trailId")
    private Trail trailId;

    //constructors

    public MapPoints() {
    }

    public MapPoints(int id, float longitude, float latitude, String mapDetails, Trail trailId) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mapDetails = mapDetails;
        this.trailId = trailId;
    }

    public MapPoints(float longitude, float latitude, String mapDetails, Trail trailId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.mapDetails = mapDetails;
        this.trailId = trailId;
    }


    // getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getMapDetails() {
        return mapDetails;
    }
    public void setMapDetails(String mapDetails) {
        this.mapDetails = mapDetails;
    }

    public Trail getTrailId() {
        return trailId;
    }
    public void setTrailId(Trail trailId) {
        this.trailId = trailId;
    }
}
