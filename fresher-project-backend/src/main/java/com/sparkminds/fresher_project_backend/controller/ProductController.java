package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.constant.PageConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductsRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping
    public ResponseEntity<ResponsePayload> searchProduct(@RequestBody SearchProductsRequest request,
                                                         @RequestParam(defaultValue = PageConstant.PAGE_NUMBER_DEFAULT) int page,
                                                         @RequestParam(defaultValue = PageConstant.PAGE_SIZE_DEFAULT) int size,
                                                         @RequestParam(defaultValue = PageConstant.SORT_BY_DEFAULT) String sortBy,
                                                         @RequestParam(defaultValue = PageConstant.SORT_BY_DIRECTION_DEFAULT_ASC) String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase(PageConstant.SORT_BY_DIRECTION_DEFAULT_ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        ResponsePayload responsePayload = productService.searchProducts(request, pageable);
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
