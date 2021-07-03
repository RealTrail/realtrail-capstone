package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.MapPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapPointsRepository extends JpaRepository<MapPoints, Long> {
    MapPoints save(MapPoints mapPoints);
    List<MapPoints> findByTrailId(Long trailId);
}
