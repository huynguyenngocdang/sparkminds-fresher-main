package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.role = :role")
    Optional<Role> findDefaultRole(@Param("role") String role);
}
