package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateBrandNameRequest;
import com.sparkminds.fresher_project_backend.dto.response.BrandResponse;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.mapper.BrandMapper;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.BrandService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final ResponsePayloadUtility responsePayloadUtility;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final BrandMapper brandMapper;

    @Override
    @Transactional
    public ResponsePayload createNewBrand(CreateBrandRequest request) {
        Optional<Brand> existingBrand = brandRepository.findByBrandName(request.getName());
        if (existingBrand.isPresent()) {
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
        Brand bradNew =  brandRepository.save(brand);
        BrandResponse brandResponse = brandMapper.toBrandResponse(bradNew);
        return responsePayloadUtility.buildResponse(
                BrandConstant.CREATE_BRAND_SUCCESSFUL,
                HttpStatus.CREATED,
                brandResponse,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload hardDeleteBrandById(DeleteBrandRequest request) {
        Brand brand = brandRepository.findByIdForWrite(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId()));

        Brand brandDefault = brandRepository.findByBrandNameForWrite(CommonConstant.NOT_ASSIGN)
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandName: " + CommonConstant.NOT_ASSIGN));

        List<Product> products = productRepository.findAllByBrandForWrite(brand);
        if(products != null && !products.isEmpty()) {
            products.forEach(product -> product.setBrand(brandDefault));
            productRepository.saveAll(products);
        }
        brandRepository.delete(brand);

        return responsePayloadUtility.buildResponse(
                BrandConstant.HARD_DELETE_BRAND_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload softDeleteBrandById(DeleteBrandRequest request) {
        Brand brand = brandRepository.findByIdForWrite(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId()));
        List<Product> products = productRepository.findAllByBrandForWrite(brand);
        brand.setDelete(true);
        Brand brandDefault = brandRepository.findByBrandNameForWrite(CommonConstant.NOT_ASSIGN)
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandName: " + CommonConstant.NOT_ASSIGN));
        products.forEach(product -> product.setBrand(brandDefault));
        Brand brandDelete = brandRepository.save(brand);
        BrandResponse brandResponse = brandMapper.toBrandResponse(brandDelete);
        productRepository.saveAll(products);
        return responsePayloadUtility.buildResponse(
                BrandConstant.SOFT_DELETE_BRAND_SUCCESSFUL,
                HttpStatus.OK,
                brandResponse,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload softRestoreBrandById(RestoreBrandRequest request) {
        Brand brand = brandRepository.findByIdForWrite(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId()));
        brand.setDelete(false);
        brandRepository.save(brand);
        return responsePayloadUtility.buildResponse(
                BrandConstant.SOFT_RESTORE_BRAND_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload updateBrandName(UpdateBrandNameRequest request) {
        Brand brand = brandRepository.findByIdForWrite(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " id: " + request.getBrandId()));
        Optional<Brand> brandValidation = brandRepository.findByBrandNameForWrite(request.getBrandNewName());

        if (brandValidation.isPresent()) {
            return responsePayloadUtility.buildResponse(
                    BrandConstant.INVALID_BRAND_ALREADY_EXIST + request.getBrandNewName(),
                    HttpStatus.BAD_REQUEST,
                    null,
                    BrandConstant.INVALID_BRAND_ALREADY_EXIST + request.getBrandNewName()
            );
        }

        brand.setBrandName(request.getBrandNewName());

        return responsePayloadUtility.buildResponse(
                BrandConstant.UPDATE_BRAND_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }
}
