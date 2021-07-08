package com.codeup.realtrail.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "map_points")
public class MapPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private String mapDetails;

    @ManyToOne
    @JoinColumn (name = "trail_id")
    @JsonManagedReference
    private Trail trail;

    //constructors
    public MapPoint() {
    }

    public MapPoint(double longitude, double latitude, Trail trail) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.trail = trail;
    }

    public MapPoint(float longitude, float latitude, String mapDetails, Trail trail) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.mapDetails = mapDetails;
        this.trail = trail;
    }

    public MapPoint(long id, float longitude, float latitude, String mapDetails, Trail trail) {
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
