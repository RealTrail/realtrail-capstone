package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository <Event, Long> {

    List<Event> getByOwner(User owner);
}
