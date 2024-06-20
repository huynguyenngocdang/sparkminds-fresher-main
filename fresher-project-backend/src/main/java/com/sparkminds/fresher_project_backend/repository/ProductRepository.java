package com.sparkminds.fresher_project_backend.repository;

import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName(String productName);
    List<Product> findAllByUser(User user);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:query% AND p.isDelete = false")
    List<Product> findProductByProductNameQuery(@Param("query") String query);
}
