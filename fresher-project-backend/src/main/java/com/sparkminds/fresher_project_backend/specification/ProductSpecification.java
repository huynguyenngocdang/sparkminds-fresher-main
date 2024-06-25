package com.sparkminds.fresher_project_backend.specification;

import com.sparkminds.fresher_project_backend.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public interface ProductSpecification {
    Specification<Product> searchProducts(String productName, String category, String brand, Double minPrice, Double maxPrice);
}
