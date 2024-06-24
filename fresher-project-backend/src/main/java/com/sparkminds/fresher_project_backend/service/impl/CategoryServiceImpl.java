package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateCategoryNameRequest;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.CategoryService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ResponsePayloadUtility responsePayloadUtility;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ResponsePayload createCategory(CreateCategoryRequest request) {
        Optional<Category> existCategory = categoryRepository.findByCategoryNameForWrite(request.getName());
        if (existCategory.isPresent()) {
            return responsePayloadUtility.buildResponse(
                    CategoryConstant.INVALID_CATEGORY_ALREADY_EXIST,
                    HttpStatus.CONFLICT,
                    null,
                    CategoryConstant.INVALID_CATEGORY_ALREADY_EXIST
            );
        }

        Category category = Category.builder()
                .categoryName(request.getName())
                .isDelete(false)
                .build();

        categoryRepository.save(category);

        return responsePayloadUtility.buildResponse(
                CategoryConstant.CREATE_CATEGORY_SUCCESSFUL,
                HttpStatus.CREATED,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload hardDeleteCategoryById(DeleteCategoryRequest request) {

        Category category = categoryRepository.findByCategoryIdForWrite(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getCategoryId()));
        List<Product> products = productRepository.findAllByCategoryForWrite(category);
        Category categoryDefault = categoryRepository.findByCategoryNameForWrite(CommonConstant.NOT_ASSIGN)
                .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryName: " + CommonConstant.NOT_ASSIGN));
        if (products != null && !products.isEmpty()) {
            products.forEach(product -> product.setCategory(categoryDefault));
            productRepository.saveAll(products);
        }
        categoryRepository.delete(category);
        return responsePayloadUtility.buildResponse(
                CategoryConstant.HARD_DELETE_CATEGORY_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload softDeleteCategoryById(DeleteCategoryRequest request) {

        Category category = categoryRepository.findByCategoryIdForWrite(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getCategoryId()));
        List<Product> products = productRepository.findAllByCategoryForWrite(category);

        Category categoryDefault = categoryRepository.findByCategoryNameForWrite(CommonConstant.NOT_ASSIGN)
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
    }
    @Override
    @Transactional
    public ResponsePayload softRestoreCategoryById(RestoreCategoryRequest request) {
        Category category = categoryRepository.findByCategoryIdForWrite(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getCategoryId()));
        category.setDelete(false);
        categoryRepository.save(category);
        return responsePayloadUtility.buildResponse(
                CategoryConstant.SOFT_RESTORE_CATEGORY_SUCCESSFUL,
                HttpStatus.OK,
                null,
                null
        );
    }

    @Override
    @Transactional
    public ResponsePayload updateCategoryName(UpdateCategoryNameRequest request) {
        Category category = categoryRepository.findByCategoryIdForWrite(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST
                        + " id: " + request.getCategoryId()));
        Optional<Category> existingCategory = categoryRepository.findByCategoryNameForWrite(request.getCategoryNewName());
        if (existingCategory.isPresent()) {
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
    }
}
