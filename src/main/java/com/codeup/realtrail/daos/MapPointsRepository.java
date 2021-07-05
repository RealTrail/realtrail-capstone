package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.MapPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapPointsRepository extends JpaRepository<MapPoint, Long> {
    List<MapPoint> findByTrailId(long trailId);
}
