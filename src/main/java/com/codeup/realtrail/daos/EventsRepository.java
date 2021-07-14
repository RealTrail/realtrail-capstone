package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository <Event, Long> {

}
