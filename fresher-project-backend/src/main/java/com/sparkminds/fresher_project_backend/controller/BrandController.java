package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateBrandService;
import com.sparkminds.fresher_project_backend.service.UpdateBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
}
