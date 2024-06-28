package com.sparkminds.fresher_project_backend.service;

import com.sparkminds.fresher_project_backend.constant.BrandConstant;
import com.sparkminds.fresher_project_backend.constant.CommonConstant;
import com.sparkminds.fresher_project_backend.controller.AuthController;
import com.sparkminds.fresher_project_backend.dto.request.CreateBrandRequest;
import com.sparkminds.fresher_project_backend.dto.request.DeleteBrandRequest;
import com.sparkminds.fresher_project_backend.dto.response.BrandResponse;
import com.sparkminds.fresher_project_backend.entity.Brand;
import com.sparkminds.fresher_project_backend.entity.Product;
import com.sparkminds.fresher_project_backend.mapper.BrandMapper;
import com.sparkminds.fresher_project_backend.payload.ResponsePayload;
import com.sparkminds.fresher_project_backend.repository.BrandRepository;
import com.sparkminds.fresher_project_backend.repository.ProductRepository;
import com.sparkminds.fresher_project_backend.service.impl.AuthServiceImpl;
import com.sparkminds.fresher_project_backend.service.impl.BrandServiceImpl;
import com.sparkminds.fresher_project_backend.utility.ResponsePayloadUtility;
import com.sparkminds.fresher_project_backend.utility.impl.ResponsePayloadUtiltyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private BrandMapper brandMapper;
    private BrandService brandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandService = new BrandServiceImpl(new ResponsePayloadUtiltyImpl(), brandRepository, productRepository, brandMapper);
    }

    @Test
    void createNewBrand_BrandAlreadyExists() {
        CreateBrandRequest request = new CreateBrandRequest("Iphone");

        when(brandRepository.findByBrandName(request.getName()))
                .thenReturn(Optional.of(new Brand()));

        ResponsePayload mockResponse = ResponsePayload.builder()
                .message(BrandConstant.INVALID_BRAND_ALREADY_EXIST)
                .status(HttpStatus.CONFLICT)
                .data(null)
                .error(BrandConstant.INVALID_BRAND_ALREADY_EXIST)
                .build();

        ResponsePayload response = brandService.createNewBrand(request);

        System.out.println("Mock Response: " + mockResponse);
        System.out.println("Response: " + response);
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatus());
        verify(brandRepository, never()).save(any(Brand.class));
    }

    @Test
    void createNewBrand_Success() {
        CreateBrandRequest request = new CreateBrandRequest("Delta");
        Brand savedBrand = Brand.builder()
                .id(1L)
                .brandName(request.getName())
                .isDelete(false)
                .build();

        BrandResponse brandResponse = BrandResponse.builder()
                .id(savedBrand.getId())
                .brandName(savedBrand.getBrandName())
                .isDelete(savedBrand.isDelete())
                .build();

        when(brandRepository.findByBrandName(request.getName()))
                .thenReturn(Optional.empty());
        when(brandRepository.save(Mockito.any(Brand.class)))
                .thenReturn(savedBrand);
        when(brandMapper.toBrandResponse(savedBrand))
                .thenReturn(brandResponse);

        ResponsePayload mockResponse = ResponsePayload.builder()
                .message(BrandConstant.CREATE_BRAND_SUCCESSFUL)
                .status(HttpStatus.CREATED)
                .data(brandResponse)
                .error(null)
                .build();


        ResponsePayload response = brandService.createNewBrand(request);

        System.out.println("Response: " + response);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(brandResponse, response.getData());
    }
    @Test
    void hardDeleteBrandById_Success() {
        DeleteBrandRequest request = new DeleteBrandRequest(1L);

        Brand brand = Brand.builder()
                .id(request.getBrandId())
                .brandName("TestBrand")
                .isDelete(false)
                .build();

        Brand brandDefault = Brand.builder()
                .id(2L)
                .brandName(CommonConstant.NOT_ASSIGN)
                .isDelete(false)
                .build();

        Product product1 = Product.builder()
                .id(1L)
                .brand(brand)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .brand(brand)
                .build();

        List<Product> products = Arrays.asList(product1, product2);
        when(brandRepository.findByIdForWrite(request.getBrandId()))
                .thenReturn(Optional.of(brand));

        when(brandRepository.findByBrandNameForWrite(CommonConstant.NOT_ASSIGN))
                .thenReturn(Optional.of(brandDefault));

        when(productRepository.findAllByBrandForWrite(brand))
                .thenReturn(products);

        when(productRepository.saveAll(anyList()))
                .thenReturn(products);

        doNothing().when(brandRepository)
                .delete(brand);

        ResponsePayload expectedResponse = ResponsePayload.builder()
                .message(BrandConstant.HARD_DELETE_BRAND_SUCCESSFUL)
                .status(HttpStatus.OK)
                .data(null)
                .error(null)
                .build();

        ResponsePayload response = brandService.hardDeleteBrandById(request);
        System.out.println("Response: " + response);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getData());
        assertEquals(BrandConstant.HARD_DELETE_BRAND_SUCCESSFUL, response.getMessage());

        verify(brandRepository).findByIdForWrite(request.getBrandId());
        verify(brandRepository).findByBrandNameForWrite(CommonConstant.NOT_ASSIGN);
        verify(productRepository).findAllByBrandForWrite(brand);
        verify(productRepository).saveAll(anyList());
        verify(brandRepository).delete(brand);
    }

    @Test
    void softDeleteBrand_Success() {
        DeleteBrandRequest request = new DeleteBrandRequest(1L);
        Brand brand = Brand.builder()
                .id(request.getBrandId())
                .brandName("Test brand")
                .isDelete(false)
                .build();
        Brand brandDefault = Brand.builder()
                .id(2L)
                .brandName(CommonConstant.NOT_ASSIGN)
                .isDelete(false)
                .build();
        Product product1 = Product.builder()
                .id(1L)
                .brand(brand)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .brand(brand)
                .build();
        List<Product> products = Arrays.asList(product1, product2);
        when(brandRepository.findByIdForWrite(request.getBrandId()))
                .thenReturn(Optional.of(brand));
        when(brandRepository.findByBrandNameForWrite(CommonConstant.NOT_ASSIGN))
                .thenReturn(Optional.of(brandDefault));
        when(productRepository.findAllByBrandForWrite(brand))
                .thenReturn(products);
        when(productRepository.saveAll(anyList()))
                .thenReturn(products);
        when(brandRepository.save(any(Brand.class)))
                .thenReturn(brand);

        ResponsePayload response = brandService.softDeleteBrandById(request);
        System.out.println("Response soft delete: " + response);

        BrandResponse brandResponse = BrandResponse.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .isDelete(true)
                .build();

        ResponsePayload expectedResponse = ResponsePayload.builder()
                .message(BrandConstant.HARD_DELETE_BRAND_SUCCESSFUL)
                .status(HttpStatus.OK)
                .data(brandResponse)
                .error(null)
                .build();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertNull(response.getData());
        assertEquals(BrandConstant.SOFT_DELETE_BRAND_SUCCESSFUL, response.getMessage());

        verify(brandRepository).findByIdForWrite(request.getBrandId());
        verify(brandRepository).findByBrandNameForWrite(CommonConstant.NOT_ASSIGN);
        verify(productRepository).findAllByBrandForWrite(brand);
        verify(productRepository).saveAll(anyList());

    }
}
