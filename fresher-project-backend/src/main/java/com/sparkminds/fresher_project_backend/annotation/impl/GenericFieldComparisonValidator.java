package com.sparkminds.fresher_project_backend.annotation.impl;

import com.sparkminds.fresher_project_backend.annotation.ComparisonType;
import com.sparkminds.fresher_project_backend.annotation.FieldComparison;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class GenericFieldComparisonValidator implements ConstraintValidator<FieldComparison, Object> {
    private String firstField;
    private String secondField;
    private ComparisonType comparisonType;
    @Override
    public void initialize(FieldComparison constraintAnnotation) {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
        this.comparisonType = constraintAnnotation.comparisonType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field firstField = value.getClass().getDeclaredField(this.firstField);
            Field secondField = value.getClass().getDeclaredField(this.secondField);
            firstField.setAccessible(true);
            secondField.setAccessible(true);

            Comparable firstValue = (Comparable) firstField.get(value);
            Comparable secondValue = (Comparable) secondField.get(value);
            if(firstValue == null || secondValue == null) {
                return true;
            }
            return comparisonType.compare(firstValue, secondValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
