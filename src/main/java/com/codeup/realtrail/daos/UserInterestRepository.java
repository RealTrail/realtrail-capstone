package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
}
