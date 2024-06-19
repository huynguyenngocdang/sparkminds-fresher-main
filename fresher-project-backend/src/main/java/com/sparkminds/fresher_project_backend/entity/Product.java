package com.sparkminds.fresher_project_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private Double price;
    private Double quantity;
    private boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "FK_BRAND_ID", referencedColumnName = "id")
    @JsonBackReference
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "FK_CATEGORY_ID", referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_USER_ID", referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
