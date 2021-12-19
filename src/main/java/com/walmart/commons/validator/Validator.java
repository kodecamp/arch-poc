package com.walmart.commons.validator;

import com.walmart.commons.service.BaseDto;
import com.walmart.commons.service.FailureDto;

import java.util.function.Supplier;


public interface Validator<T extends BaseDto> {
    public ValidationResult validate(BaseDto obj);

    static ValidationResult ifTrue(Supplier<Boolean> condition, FailureDto failureDto) {
        return condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

    static ValidationResult ifFalse(Supplier<Boolean> condition, FailureDto failureDto) {
        return !condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

}
