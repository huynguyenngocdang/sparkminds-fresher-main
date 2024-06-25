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

    @Query("SELECT p FROM Product p WHERE (p.productName LIKE %:query% " +
            "OR p.category.categoryName LIKE %:query% " +
            "OR p.brand.brandName LIKE %:query% " +
            "OR p.user.username LIKE %:query% " +
            "OR p.user.userProfile.fullName LIKE %:query%) " +
            "AND p.isDelete = false")
    List<Product> findProductByProductNameQuery(@Param("query") String query);

    @Query("SELECT p FROM Product p WHERE (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND p.isDelete = false")
    List<Product> findProductByDynamicPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product  p WHERE p.category.id IN :categoryIds AND p.isDelete = false")
    List<Product> findProductsByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.brand = :brand")
    List<Product> findAllByBrandForWrite(@Param("brand") Brand brand);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findAllByCategoryForWrite(@Param("category") Category category);
}

