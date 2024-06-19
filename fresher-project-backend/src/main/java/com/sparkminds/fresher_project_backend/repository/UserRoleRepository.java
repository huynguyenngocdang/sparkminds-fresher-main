package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
