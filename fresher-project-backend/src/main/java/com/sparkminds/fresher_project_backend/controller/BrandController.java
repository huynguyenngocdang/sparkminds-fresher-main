package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.BrandService;
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
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;
    @PostMapping
    public ResponseEntity<ResponsePayload> createBrand(@RequestBody @Valid CreateBrandRequest request) {
        ResponsePayload responsePayload = brandService.createNewBrand(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping
    public ResponseEntity<ResponsePayload> updateBrandName(@RequestBody @Valid UpdateBrandNameRequest request) {
        ResponsePayload responsePayload = brandService.updateBrandName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard")
    public ResponseEntity<ResponsePayload> hardDeleteBrand(@RequestBody @Valid DeleteBrandRequest request) {
        ResponsePayload responsePayload = brandService.hardDeleteBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    //nhung cai nao co id roi thi de vao pathvariable {id}
    @DeleteMapping
    public ResponseEntity<ResponsePayload> softDeleteBrand(@RequestBody @Valid DeleteBrandRequest request) {
        ResponsePayload responsePayload = brandService.softDeleteBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping
    public ResponseEntity<ResponsePayload> softRestoreBrand(@RequestBody @Valid RestoreBrandRequest request) {
        ResponsePayload responsePayload = brandService.softRestoreBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
