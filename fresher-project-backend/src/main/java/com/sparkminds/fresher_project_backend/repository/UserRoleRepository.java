package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUser(User user);
}
