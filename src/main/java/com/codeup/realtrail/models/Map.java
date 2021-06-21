package com.codeup.realtrail.models;


import javax.persistence.*;

@Entity
@Table(name = "maps")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private float longitude;
    @Column
    private float latitude;
    @Column
    private String map_details;
}
