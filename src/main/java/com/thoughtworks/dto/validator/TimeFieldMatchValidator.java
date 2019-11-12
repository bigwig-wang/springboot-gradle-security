package com.thoughtworks.dto.validator;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class TimeFieldMatchValidator implements ConstraintValidator<TimeFieldMatch, Object> {

    private String startTimeField;
    private String endTimeField;

    @Override
    public void initialize(TimeFieldMatch timeFieldMatch) {
        this.startTimeField = timeFieldMatch.startTimeField();
        this.endTimeField = timeFieldMatch.endTimeField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        if(beanWrapper.getPropertyValue(startTimeField) == null
                || beanWrapper.getPropertyValue(endTimeField) == null) {
            return true;
        }
        Date startTime = (Date) beanWrapper.getPropertyValue(startTimeField);
        Date endTime = (Date) beanWrapper.getPropertyValue(endTimeField);
        if(startTime.after(endTime)) {
            return false;
        }
        return true;
    }
}
