package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Brand;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Brand b WHERE b.brandName = :brandName")
    Optional<Brand> findByBrandNameForWrite(@Param("brandName") String brandName);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM Brand b WHERE b.id = :brandId")
    Optional<Brand> findByIdForWrite(@Param("brandId") Long brandId);
    Optional<Brand> findByBrandName(String brandName);
}
