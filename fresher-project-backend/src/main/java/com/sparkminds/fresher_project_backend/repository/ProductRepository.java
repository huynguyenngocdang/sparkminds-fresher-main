package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.entity.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    boolean existsByProductName(String productName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.user = :user")
    List<Product> findAllByUserForWrite(@Param("user") User user);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.brand = :brand")
    List<Product> findAllByBrandForWrite(@Param("brand") Brand brand);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findAllByCategoryForWrite(@Param("category") Category category);
}

