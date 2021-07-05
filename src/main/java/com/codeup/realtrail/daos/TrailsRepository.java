package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TrailsRepository extends JpaRepository<Trail, Long> {
    Trail findTrailByName(String name);
<<<<<<< HEAD
    Trail findById(long id);
=======
    Trail findTrailById(Long id);

    List<Trail> findAllByDifficultyLevel(String difficultyLevel);

>>>>>>> fc0bb6818ec07ca507a92115ae2bf03903d7b6b2
}
