package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.service.CreateBrandService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBrandServiceImpl implements CreateBrandService {
    private final BrandRepository brandRepository;
    private final ResponsePayloadUtility responsePayloadUtility;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload createNewBrand(CreateBrandRequest request) {
        try {
            if(brandRepository.existsByBrandName(request.getName())) {
                return responsePayloadUtility.buildResponse(
                        BrandConstant.INVALID_BRAND_ALREADY_EXIST,
                        HttpStatus.CONFLICT,
                        null,
                        BrandConstant.INVALID_BRAND_ALREADY_EXIST
                );
            }
            Brand brand = Brand.builder()
                    .brandName(request.getName())
                    .build();
            brandRepository.save(brand);
            return responsePayloadUtility.buildResponse(
                    BrandConstant.CREATE_BRAND_SUCCESSFUL,
                    HttpStatus.CREATED,
                    null,
                    null
            );

        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    BrandConstant.CREATE_BRAND_FAIL,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    BrandConstant.CREATE_BRAND_FAIL
            );
        }
    }
}
