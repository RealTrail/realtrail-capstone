package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Trail;
import com.codeup.realtrail.models.TrailComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrailsRepository extends JpaRepository<Trail, Long> {
    Trail findTrailByName(String name);
    Trail findById(long id);
    Trail findByNameContains(String name);


    @Query("SELECT t FROM Trail t WHERE t.name LIKE %:keyword%")
    List<Trail> findByName(@Param("keyword") String keyword);

    @Query("SELECT t FROM Trail t WHERE t.difficultyLevel = ?1")
    List<Trail> findByDifficultyLevel(String diffLevel);

    @Query("SELECT t FROM Trail t WHERE t.type = ?1")
    List<Trail> findByType(String type);

    @Query("SELECT t FROM Trail t WHERE t.difficultyLevel = ?1 AND t.type = ?2")
    List<Trail> findByDifficultyLevelAndType(String difficultyLevel, String type);
}
