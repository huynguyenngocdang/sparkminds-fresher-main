package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.service.CategoryService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ResponsePayloadUtility responsePayloadUtility;
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload createCategory(CreateCategoryRequest request) {
        try {
            if(categoryRepository.existsByCategoryName(request.getName())) {
                return responsePayloadUtility.buildResponse(
                        CategoryConstant.INVALID_CATEGORY_ALREADY_EXIST,
                        HttpStatus.CONFLICT,
                        null,
                        CategoryConstant.INVALID_CATEGORY_ALREADY_EXIST
                );
            }

            Category category = Category.builder()
                    .categoryName(request.getName())
                    .categoryBrands(new ArrayList<>())
                    .isDelete(false)
                    .build();

            categoryRepository.save(category);

            return responsePayloadUtility.buildResponse(
                    CategoryConstant.CREATE_CATEGORY_SUCCESSFUL,
                    HttpStatus.CREATED,
                    null,
                    null
            );
        } catch (Exception e) {
            return responsePayloadUtility.buildResponse(
                    CategoryConstant.CREATE_CATEGORY_FAIL,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    CategoryConstant.CREATE_CATEGORY_FAIL
            );
        }
    }
}
