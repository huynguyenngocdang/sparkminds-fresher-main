package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
