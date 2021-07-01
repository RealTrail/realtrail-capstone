package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.MapPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapPointsRepository extends JpaRepository<MapPoints, Long> {
    MapPoints save(MapPoints mapPoints);
}
