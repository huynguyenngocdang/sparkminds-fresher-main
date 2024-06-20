package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateProductService;
import com.sparkminds.fresher_project_backend.service.SearchProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
}
