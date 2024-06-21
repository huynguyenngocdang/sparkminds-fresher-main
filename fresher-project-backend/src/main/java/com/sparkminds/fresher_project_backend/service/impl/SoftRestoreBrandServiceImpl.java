package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.service.SoftRestoreBrandService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftRestoreBrandServiceImpl implements SoftRestoreBrandService {
    private final BrandRepository brandRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload softRestoreBrandById(RestoreBrandRequest request) {
        try {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId()));
            brand.setDelete(true);
            brandRepository.save(brand);
            return responsePayloadUtility.buildResponse(
                    BrandConstant.SOFT_RESTORE_BRAND_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    BrandConstant.SOFT_RESTORE_BRAND_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
