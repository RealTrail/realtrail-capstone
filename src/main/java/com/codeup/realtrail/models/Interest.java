package com.codeup.realtrail.models;


import javax.persistence.*;

@Entity
@Table(name = "interests")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column()
    private String interest;

    // constructors
    public Interest() {
    }
    public Interest(int id, String interest) {
        this.id = id;
        this.interest = interest;
    }


    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }
}
