package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CategoryConstant;
import com.sparkminds.fresher_project_backend.constant.ProductConstant;
import com.sparkminds.fresher_project_backend.constant.ProductSearchConstant;
import com.sparkminds.fresher_project_backend.constant.UserConstant;
import com.sparkminds.fresher_project_backend.dto.request.CreateProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.RestoreProductRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByNameRequest;
import com.sparkminds.fresher_project_backend.dto.request.SearchProductByPriceRangeRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductCategoryRequest;
import com.sparkminds.fresher_project_backend.dto.request.UpdateProductDetailsRequest;
import com.sparkminds.fresher_project_backend.dto.response.SearchProductResponse;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Category;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.exception.ResourceNotFoundException;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.CreateProductBrandExistValidationHandlerImpl;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.CreateProductCategoryExistValidationHandlerImpl;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.CreateProductProductExistValidationHandlerImpl;
import com.sparkminds.fresher_project_backend.handler.createProductHandler.impl.CreateProductUserExistValidationHandlerImpl;
import com.sparkminds.fresher_project_backend.mapper.ProductMapper;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.CategoryRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.repository.UserRepository;
import com.sparkminds.fresher_project_backend.service.ProductService;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ResponsePayloadUtility responsePayloadUtility;
    private final ProductMapper productMapper;

    private final CreateProductBrandExistValidationHandlerImpl createProductBrandExistValidationHandlerImpl;
    private final CreateProductCategoryExistValidationHandlerImpl createProductCategoryExistValidationHandlerImpl;
    private final CreateProductProductExistValidationHandlerImpl createProductProductExistValidationHandlerImpl;
    private final CreateProductUserExistValidationHandlerImpl createProductUserExistValidationHandlerImpl;

    @Override
    @Transactional
    public ResponsePayload createNewProduct(CreateProductRequest request) {

            createProductUserExistValidationHandlerImpl.setNextHandler(createProductBrandExistValidationHandlerImpl);
            createProductBrandExistValidationHandlerImpl.setNextHandler(createProductCategoryExistValidationHandlerImpl);
            createProductCategoryExistValidationHandlerImpl.setNextHandler(createProductProductExistValidationHandlerImpl);
            createProductProductExistValidationHandlerImpl.setNextHandler(null);

            ResponsePayload responsePayload = createProductUserExistValidationHandlerImpl.handle(request);
            if(!responsePayload.getStatus().is2xxSuccessful()) {
                return responsePayload;
            }

            Product product = productMapper.createRequestToProduct(request);
            product.setBrand(brandRepository.findById(
                    request.getBrandId()).orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getBrandId())));
            product.setCategory(categoryRepository.findById(
                    request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getCategoryId())));
            product.setUser(userRepository.findById(
                    request.getUserId()).orElseThrow(() -> new ResourceNotFoundException(UserConstant.INVALID_USER_NOT_EXIST + " userId: " + request.getUserId())));

            productRepository.save(product);

            return responsePayloadUtility.buildResponse(
                    ProductConstant.CREATE_PRODUCT_SUCCESSFUL,
                    HttpStatus.CREATED,
                    null,
                    null
            );
    }
    @Override
    public ResponsePayload searchProductsByName(SearchProductByNameRequest request) {
        String formatQuery = request.getQuery().trim().toLowerCase();
        List<Product> products = productRepository.findProductByProductNameQuery(formatQuery);
        List<SearchProductResponse> productResponses = productMapper.toSearchProductResponseList(products);

        return responsePayloadUtility.buildResponse(
                ProductSearchConstant.PRODUCT_SEARCH_SUCCESS,
                HttpStatus.OK,
                productResponses,
                null
        );
    }

    @Override
    public ResponsePayload searchProductByPrice(SearchProductByPriceRangeRequest request) {
        List<Product> products = productRepository.findProductByDynamicPriceRange(request.getPriceMin(), request.getPriceMax());
        List<SearchProductResponse> productResponses = productMapper.toSearchProductResponseList(products);

        return responsePayloadUtility.buildResponse(
                ProductSearchConstant.PRODUCT_SEARCH_SUCCESS,
                HttpStatus.OK,
                productResponses,
                null
        );
    }

    @Override
    public ResponsePayload searchProductByCategory(SearchProductByCategoryRequest request) {
        List<Product> products = productRepository.findProductsByCategoryIds(request.getCategoryIds());
        List<SearchProductResponse> productResponses = productMapper.toSearchProductResponseList(products);

        return responsePayloadUtility.buildResponse(
                ProductSearchConstant.PRODUCT_SEARCH_SUCCESS,
                HttpStatus.OK,
                productResponses,
                null
        );
    }
    @Override
    @Transactional
    public ResponsePayload hardDeleteProductById(DeleteProductRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException( ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            productRepository.delete(product);

            return responsePayloadUtility.buildResponse(
                    ProductConstant.HARD_DELETE_PRODUCT_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }
    @Override
    @Transactional
    public ResponsePayload softDeleteProductById(DeleteProductRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));
            product.setDelete(true);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.SOFT_DELETE_PRODUCT_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }

    @Override
    @Transactional
    public ResponsePayload softRestoreProductById(RestoreProductRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            product.setDelete(false);
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.SOFT_RESTORE_PRODUCT_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }
    @Override
    @Transactional
    public ResponsePayload updateProductDetails(UpdateProductDetailsRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " id: " + request.getProductId()));

            if(productRepository.existsByProductName(request.getProductName())) {
                return responsePayloadUtility.buildResponse(
                        ProductConstant.UPDATE_PRODUCT_DETAILS_FAIL,
                        HttpStatus.BAD_REQUEST,
                        null,
                        ProductConstant.INVALID_PRODUCT_ALREADY_EXIST + request.getProductName()
                );
            }

            product.setProductName(request.getProductName());
            product.setPrice(request.getPrice());
            product.setQuantity(request.getQuantity());

            productRepository.save(product);

            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_DETAILS_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }

    @Override
    @Transactional
    public ResponsePayload updateProductBrand(UpdateProductBrandRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            Brand brand = brandRepository.findById(request.getNewBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException(BrandConstant.INVALID_BRAND_NOT_EXIST + " brandId: " + request.getNewBrandId()));

            product.setBrand(brand);
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_BRAND_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }

    @Override
    @Transactional
    public ResponsePayload updateProductCategory(UpdateProductCategoryRequest request) {

            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductConstant.INVALID_PRODUCT_NOT_EXIST + " productId: " + request.getProductId()));

            Category category = categoryRepository.findById(request.getNewCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(CategoryConstant.INVALID_CATEGORY_NOT_EXIST + " categoryId: " + request.getNewCategoryId()));

            product.setCategory(category);
            productRepository.save(product);
            return responsePayloadUtility.buildResponse(
                    ProductConstant.UPDATE_PRODUCT_CATEGORY_SUCCESSFUL,
                    HttpStatus.OK,
                    null,
                    null
            );
    }
}
