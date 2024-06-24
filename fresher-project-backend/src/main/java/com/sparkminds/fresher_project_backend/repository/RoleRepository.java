package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Role;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Role r WHERE r.role = :role")
    Optional<Role> findDefaultRoleForWrite(@Param("role") String role);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT r FROM Role r JOIN r.userRoles ur JOIN ur.user u WHERE u.username =:username")
    List<Role> findAllRoleForRead(@Param("username") String username);
}
