package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventParticipantsRepository extends JpaRepository<Event, Long> {
//    void deleteByEventId(long id);
//    @Query("DELETE FROM events_participants WHERE event_id = id")
//    void deleteByEventId(long id);
}
