package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EventsRepository extends JpaRepository <Event, Long> {

    Event findEventById(long id);
    Event findEventByLocation(String location);
    Event deleteEventByName(String name);



    List<Event> findAllByOwner(User owner);


    List<Event> searchByName(String name);

//    void update(Event event);


//    Event saveEvent(Event event);
//    @Modifying
//    @Transactional
//    @Query("UPDATE Event e SET e.name= :getName, e.date= :getDate, e.time= :getTime, e.location= :getLocation, e.trail= :getTrail, e.meetLocation= :getMeetLocation, e.meetTime= :getMeetTime, e.eventDetails= :getEventDetails, e.images= :getImages, e.participants= :getParticipants")



}
