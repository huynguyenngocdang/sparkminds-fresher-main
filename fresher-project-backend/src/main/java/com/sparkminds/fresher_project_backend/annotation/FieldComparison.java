package com.sparkminds.fresher_project_backend.annotation;

import com.sparkminds.fresher_project_backend.annotation.impl.GenericFieldComparisonValidator;
import com.sparkminds.fresher_project_backend.constant.CommonValidationConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = GenericFieldComparisonValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldComparison {
    String message() default CommonValidationConstant.FIELD_COMPARISON_FAILED;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String firstField();
    String secondField();
    ComparisonType comparisonType();
}
