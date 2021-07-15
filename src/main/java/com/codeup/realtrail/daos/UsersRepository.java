package com.codeup.realtrail.daos;

import com.codeup.realtrail.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Override
    void delete(User user);
}
