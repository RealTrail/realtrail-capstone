package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.TrailComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrailCommentsRepository extends JpaRepository<TrailComment, Long> {

    List<TrailComment> getAllByTrailId(Long id);
}
