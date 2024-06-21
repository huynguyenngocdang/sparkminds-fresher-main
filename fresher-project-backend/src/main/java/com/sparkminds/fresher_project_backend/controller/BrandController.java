package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateBrandService;
import com.sparkminds.fresher_project_backend.service.HardDeleteBrandService;
import com.sparkminds.fresher_project_backend.service.SoftDeleteBrandService;
import com.sparkminds.fresher_project_backend.service.SoftRestoreBrandService;
import com.sparkminds.fresher_project_backend.service.UpdateBrandService;
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

    private final CreateBrandService createBrandService;
    private final UpdateBrandService updateBrandService;
    private final HardDeleteBrandService hardDeleteBrandService;
    private final SoftDeleteBrandService softDeleteBrandService;
    private final SoftRestoreBrandService softRestoreBrandService;
    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createBrand(@RequestBody @Valid CreateBrandRequest request) {
        ResponsePayload responsePayload = createBrandService.createNewBrand(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PutMapping("/update")
    public ResponseEntity<ResponsePayload> updateBrandName(@RequestBody @Valid UpdateBrandNameRequest request) {
        ResponsePayload responsePayload = updateBrandService.updateBrandName(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/hard-delete-brand")
    public ResponseEntity<ResponsePayload> hardDeleteBrand(@RequestBody @Valid DeleteBrandRequest request) {
        ResponsePayload responsePayload = hardDeleteBrandService.hardDeleteBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @DeleteMapping("/soft-delete-brand")
    public ResponseEntity<ResponsePayload> softDeleteBrand(@RequestBody @Valid DeleteBrandRequest request) {
        ResponsePayload responsePayload = softDeleteBrandService.softDeleteBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
    @PatchMapping("/soft-restore-brand")
    public ResponseEntity<ResponsePayload> softRestoreBrand(@RequestBody @Valid RestoreBrandRequest request) {
        ResponsePayload responsePayload = softRestoreBrandService.softRestoreBrandById(request);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
