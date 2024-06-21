package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.SoftDeleteCategoryService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoftDeleteCategoryServiceImpl implements SoftDeleteCategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload softDeleteCategoryById(DeleteCategoryRequest request) {
        try {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getCategoryId()));
            List<Product> products = productRepository.findAllByCategory(category);

            Category categoryDefault = categoryRepository.findByCategoryName(CommonConstant.NOT_ASSIGN)
                            .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryName: " + CommonConstant.NOT_ASSIGN));
            category.setDelete(true);
            products.forEach(product -> product.setCategory(categoryDefault));

            categoryRepository.save(category);
            productRepository.saveAll(products);
            return responsePayloadUtility.buildResponse(
                    CategoryConstant.SOFT_DELETE_CATEGORY_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    CategoryConstant.SOFT_DELETE_CATEGORY_FAIL,
                    HttpStatus.BAD_REQUEST,
                    null,
                    e.getMessage()
            );
        }
    }
}
