package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ResponsePayload> createNewProduct(@RequestBody @Valid CreateProductRequest request) {
        ResponsePayload responsePayload = productService.createNewProduct(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponsePayload> searchProductByName(@RequestBody @Valid SearchProductByNameRequest request) {
        ResponsePayload responsePayload = productService.searchProductsByName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @GetMapping("/price")
    public ResponseEntity<ResponsePayload> searchProductByPrice(@RequestBody @Valid SearchProductByPriceRangeRequest request) {
        ResponsePayload responsePayload = productService.searchProductByPrice(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @GetMapping("/categories")
    public ResponseEntity<ResponsePayload> searchProductByCategory(@RequestBody @Valid SearchProductByCategoryRequest request) {
        ResponsePayload responsePayload = productService.searchProductByCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping
    public ResponseEntity<ResponsePayload> updateProductDetails(@RequestBody @Valid UpdateProductDetailsRequest request) {
        ResponsePayload responsePayload = productService.updateProductDetails(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping("/brand")
    public ResponseEntity<ResponsePayload> updateProductBrand(@RequestBody @Valid UpdateProductBrandRequest request) {
        ResponsePayload responsePayload = productService.updateProductBrand(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping("/category")
    public ResponseEntity<ResponsePayload> updateProductCategory(@RequestBody @Valid UpdateProductCategoryRequest request) {
        ResponsePayload responsePayload = productService.updateProductCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard")
    public ResponseEntity<ResponsePayload> hardDeleteProductById(@RequestBody @Valid DeleteProductRequest request) {
        ResponsePayload responsePayload = productService.hardDeleteProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping
    public ResponseEntity<ResponsePayload> softDeleteProductById(@RequestBody @Valid DeleteProductRequest request) {
        ResponsePayload responsePayload = productService.softDeleteProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping
    public ResponseEntity<ResponsePayload> softRestoreProductById(@RequestBody @Valid RestoreProductRequest request) {
        ResponsePayload responsePayload = productService.softRestoreProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
