package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.BrandValidationConstant;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.service.UpdateBrandService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBrandServiceImpl implements UpdateBrandService {
    private final BrandRepository brandRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateBrandName(UpdateBrandNameRequest request) {
        try {

            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " id: " + request.getBrandId()));

            if(brandRepository.existsByBrandName(request.getBrandNewName())) {
                return responsePayloadUtility.buildResponse(
                        BrandConstant.INVALID_BRAND_ALREADY_EXIST + request.getBrandNewName(),
                        HttpStatus.BAD_REQUEST,
                        null,
                        BrandConstant.INVALID_BRAND_ALREADY_EXIST + request.getBrandNewName()
                );
            }

            brand.setBrandName(request.getBrandNewName());

            brandRepository.save(brand);
            return responsePayloadUtility.buildResponse(
                    BrandConstant.UPDATE_BRAND_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    BrandConstant.UPDATE_BRAND_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
