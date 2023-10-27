package com.example.pub.repos;

import com.example.pub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User, Long> {

    User findUserById(Long id);
    User findUserByName(String name);
    boolean existsByUsername(String username);
}
