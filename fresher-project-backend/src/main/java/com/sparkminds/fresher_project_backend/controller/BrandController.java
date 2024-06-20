package com.sparkminds.fresher_project_backend.controller;

import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.service.CreateBrandService;
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
@RequestMapping("/api/brands")
public class BrandController {

    private final CreateBrandService createBrandService;
    @PostMapping("/create")
    public ResponseEntity<ResponsePayload> createBrand(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
        ResponsePayload responsePayload = createBrandService.createNewBrand(createBrandRequest);
        return new ResponseEntity<>(responsePayload, responsePayload.getStatus());
    }
}
