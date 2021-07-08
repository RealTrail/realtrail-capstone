package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventParticipantsRepository extends JpaRepository<User, Long> {

}
