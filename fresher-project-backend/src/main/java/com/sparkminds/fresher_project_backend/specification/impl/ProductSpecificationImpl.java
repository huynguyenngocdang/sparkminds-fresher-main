package com.sparkminds.fresher_project_backend.specification.impl;

import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.specification.ProductSpecification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductSpecificationImpl implements ProductSpecification {
    @Override
    // edit specification cho them common, vi du search category list thay vi search 1 category
    public Specification<Product> searchProducts(String productName, String category, String brand, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(productName)) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%"));
            }
            if (StringUtils.hasText(category)) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("categoryName"), category));
            }
            if (StringUtils.hasText(brand)) {
                predicates.add(criteriaBuilder.equal(root.get("brand").get("brandName"), brand));
            }
            if(minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if(maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
