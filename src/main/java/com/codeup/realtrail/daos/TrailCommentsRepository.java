package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Event;
import com.codeup.realtrail.models.TrailComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailCommentsRepository extends JpaRepository<TrailComment, Long> {

}
