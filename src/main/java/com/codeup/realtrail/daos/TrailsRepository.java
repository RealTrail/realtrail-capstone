package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrailsRepository extends JpaRepository<Trail, Long> {
    Trail findTrailByName(String name);
    Trail findById(long id);
    List<Trail> findAllByDifficultyLevel(String level);
    List<Trail> findAllByType(String routeType);

    @Query("FROM Trail t WHERE t.name LIKE %:keyword%")
    Trail findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT difficultyLevel FROM Trail")
    List<String> findByDifficultyLevel();

    List<Trail> findAllByDifficultyLevelAndAndTypeAndName(String difficultyLevel, String type, String name);
}
