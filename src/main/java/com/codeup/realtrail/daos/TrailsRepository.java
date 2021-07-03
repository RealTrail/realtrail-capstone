package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Trail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrailsRepository extends JpaRepository<Trail, Long> {
    Trail findTrailByName(String name);
    Trail findById(long id);
}
