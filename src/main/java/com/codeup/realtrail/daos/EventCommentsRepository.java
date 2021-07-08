package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentsRepository extends JpaRepository<EventComment, Long> {
}
