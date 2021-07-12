package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.Trail;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventParticipantsRepository extends JpaRepository<User, Long> {
//    @Query("DELETE FROM events_participants WHERE event_id = id")
//    void deleteByEventId(long id);
}
