package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.PictureURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureURLsRepository extends JpaRepository<PictureURL, Long> {
}
