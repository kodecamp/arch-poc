package com.walmart.commons.validator;

import com.walmart.commons.dto.FailureDto;

import java.util.function.Supplier;


public interface Validator {
    ValidationResult validate(Object obj);

    static ValidationResult ifTrue(Supplier<Boolean> condition, FailureDto failureDto) {
        return condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

    static ValidationResult ifTrue(Supplier<Boolean> condition, String errorCode) {
        return condition.get()
                ? ValidationResult.from(FailureDto.from(errorCode))
                : ValidationResult.success();

    }

    static ValidationResult ifFalse(Supplier<Boolean> condition, FailureDto failureDto) {
        return !condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

}
