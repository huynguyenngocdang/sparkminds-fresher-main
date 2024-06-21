package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.service.UpdateCategoryService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCategoryServiceImpl implements UpdateCategoryService {
    private final CategoryRepository categoryRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponsePayload updateCategoryName(UpdateCategoryNameRequest request) {
       try {
           Category category = categoryRepository.findById(request.getCategoryId())
                   .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST
                         +" id: "  + request.getCategoryId()));

           if(categoryRepository.existsByCategoryName(request.getCategoryNewName())) {
               return responsePayloadUtility.buildResponse(
                       CategoryConstant.UPDATE_CATEGORY_FAIL,
                       HttpStatus.BAD_REQUEST,
                       null,
                       CategoryConstant.INVALID_CATEGORY_ALREADY_EXIST
               );
           }

           category.setCategoryName(request.getCategoryNewName());
           categoryRepository.save(category);
           return responsePayloadUtility.buildResponse(
                   CategoryConstant.UPDATE_CATEGORY_SUCCESSFUL,
                   HttpStatus.OK,
                   null,
                   null
           );
       } catch (Exception e) {
           return responsePayloadUtility.buildResponse(
                   CategoryConstant.UPDATE_CATEGORY_FAIL,
                   HttpStatus.BAD_REQUEST,
                   null,
                   e.getMessage()
           );
       }
    }
}
