package com.sparkminds.fresher_project_backend.constant;

public class ProductValidationConstant extends CommonValidationConstant{

    public static final String PRODUCT_ID_NOT_EMPTY = "Product id " + VALIDATION_FAIL_NOT_EMPTY;
    public static final String PRODUCT_NAME_NOT_BLANK = "Product name " + VALIDATION_FAIL_NOT_BLANK;
    public static final String PRODUCT_NAME_NOT_EMPTY = "Product name " + VALIDATION_FAIL_NOT_EMPTY;
    public static final String PRODUCT_PRICE_NOT_EMPTY = "Product price " + VALIDATION_FAIL_NOT_EMPTY;
    public static final String PRODUCT_PRICE_NOT_NEGATIVE = "Product price " + VALIDATION_FAIL_GREATER_0;
    public static final String PRODUCT_QUANTITY_NOT_EMPTY = "Product quantity " + VALIDATION_FAIL_NOT_EMPTY;
    public static final String PRODUCT_QUANTITY_NOT_NEGATIVE = "Product quantity " + VALIDATION_FAIL_GREATER_0;
}
