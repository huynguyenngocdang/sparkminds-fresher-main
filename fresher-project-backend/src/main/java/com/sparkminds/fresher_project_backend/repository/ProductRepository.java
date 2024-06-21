package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);
    List<Product> findAllByUser(User user);
    List<Product> findAllByBrand(Brand brand);
    List<Product> findAllByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:query% AND p.isDelete = false")
    List<Product> findProductByProductNameQuery(@Param("query") String query);

    @Query("SELECT p FROM Product p WHERE (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND p.isDelete = false")
    List<Product> findProductByDynamicPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product  p WHERE p.category.id IN :categoryIds AND p.isDelete = false")
    List<Product> findProductsByCategoryIds(@Param("categoryIds") List<Long> categoryIds);
}

