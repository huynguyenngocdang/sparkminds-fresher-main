package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.HardDeleteBrandService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HardDeleteBrandServiceImpl implements HardDeleteBrandService {
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload hardDeleteBrandById(DeleteBrandRequest request) {
        try {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId()));
            List<Product> products = productRepository.findAllByBrand(brand);
            brandRepository.delete(brand);
//            productRepository.deleteAll(products);
            Brand brandDefault = brandRepository.findByBrandName(CommonConstant.NOT_ASSIGN)
                    .orElseThrow(()-> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandName: " + CommonConstant.NOT_ASSIGN));
            products.forEach(product -> product.setBrand(brandDefault));
            productRepository.saveAll(products);

            return responsePayloadUtility.buildResponse(
                    BrandConstant.HARD_DELETE_BRAND_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    BrandConstant.HARD_DELETE_BRAND_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
