package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
