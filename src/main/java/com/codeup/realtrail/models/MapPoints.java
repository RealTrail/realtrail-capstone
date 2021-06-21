package com.codeup.realtrail.models;


import javax.persistence.*;

@Entity
@Table(name = "map_points")
public class MapPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private float longitude;

    @Column
    private float latitude;

    @Column
    private String mapDetails;

    @OneToOne
    private Trail trail;

    //constructors

    public MapPoints() {
    }

    public MapPoints(float longitude, float latitude, String mapDetails, Trail trail) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.mapDetails = mapDetails;
        this.trail = trail;
    }

    public MapPoints(long id, float longitude, float latitude, String mapDetails, Trail trail) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mapDetails = mapDetails;
        this.trail = trail;
    }

    // getters and setters

    public long getId() {
        return id;
    }
    public void setId(long id) {
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

    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }
}
