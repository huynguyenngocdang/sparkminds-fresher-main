package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.constant.ProductValidationConstant;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.HardDeleteProductService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HardDeleteProductServiceImpl implements HardDeleteProductService {
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload hardDeleteProductById(DeleteProductRequest request) {
        try {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException( ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            productRepository.delete(product);

            return responsePayloadUtility.buildResponse(
                    ProductConstant.HARD_DELETE_PRODUCT_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    ProductConstant.HARD_DELETE_PRODUCT_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
