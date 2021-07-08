package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EventsRepository extends JpaRepository <Event, Long> {

}
