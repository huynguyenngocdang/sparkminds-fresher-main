package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.User;
import com.sparkminds.fresher_project_backend.entity.UserRole;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ur FROM UserRole ur WHERE ur.user = :user")
    List<UserRole> findAllByUserForWrite(@Param("user") User user);
}
