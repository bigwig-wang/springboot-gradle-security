package com.thoughtworks.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = TimeFieldMatchValidator.class)
@Documented
public @interface TimeFieldMatch {
    String startTimeField();

    String endTimeField();

    String message() default "error message";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
