package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateProductService;
import com.sparkminds.fresher_project_backend.service.HardDeleteProductService;
import com.sparkminds.fresher_project_backend.service.SearchProductService;
import com.sparkminds.fresher_project_backend.service.SoftDeleteProductService;
import com.sparkminds.fresher_project_backend.service.SoftRestoreProductService;
import com.sparkminds.fresher_project_backend.service.UpdateProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    private final CreateProductService createProductService;
    private final SearchProductService searchProductService;
    private final UpdateProductService updateProductService;
    private final HardDeleteProductService hardDeleteProductService;
    private final SoftDeleteProductService softDeleteProductService;
    private final SoftRestoreProductService softRestoreProductService;

    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createNewProduct(@RequestBody @Valid CreateProductRequest request) {
        ResponsePayload responsePayload = createProductService.createNewProduct(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PostMapping("/search-by-name")
    public ResponseEntity<ResponsePayload> searchProductByName(@RequestBody @Valid SearchProductByNameRequest request) {
        ResponsePayload responsePayload = searchProductService.searchProductsByName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PostMapping("/search-by-price")
    public ResponseEntity<ResponsePayload> searchProductByPrice(@RequestBody @Valid SearchProductByPriceRangeRequest request) {
        ResponsePayload responsePayload = searchProductService.searchProductByPrice(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping("/update-details")
    public ResponseEntity<ResponsePayload> updateProductDetails(@RequestBody @Valid UpdateProductDetailsRequest request) {
        ResponsePayload responsePayload = updateProductService.updateProductDetails(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }

    @PutMapping("/update-brand")
    public ResponseEntity<ResponsePayload> updateProductBrand(@RequestBody @Valid UpdateProductBrandRequest request) {
        ResponsePayload responsePayload = updateProductService.updateProductBrand(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping("/update-category")
    public ResponseEntity<ResponsePayload> updateProductCategory(@RequestBody @Valid UpdateProductCategoryRequest request) {
        ResponsePayload responsePayload = updateProductService.updateProductCategory(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard-delete-product")
    public ResponseEntity<ResponsePayload> hardDeleteProductById(@RequestBody @Valid DeleteProductRequest request) {
        ResponsePayload responsePayload = hardDeleteProductService.hardDeleteProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/soft-delete-product")
    public ResponseEntity<ResponsePayload> softDeleteProductById(@RequestBody @Valid DeleteProductRequest request) {
        ResponsePayload responsePayload = softDeleteProductService.softDeleteProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping("/soft-restore-product")
    public ResponseEntity<ResponsePayload> softRestoreProductById(@RequestBody @Valid RestoreProductRequest request) {
        ResponsePayload responsePayload = softRestoreProductService.softRestoreProductById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
