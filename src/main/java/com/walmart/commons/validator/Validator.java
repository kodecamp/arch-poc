package com.walmart.commons.validator;

import com.walmart.commons.dto.ErrorDto;

import java.util.function.Supplier;


public interface Validator {
    ValidationResult validate(Object obj);

    static ValidationResult ifTrue(Supplier<Boolean> condition, ErrorDto failureDto) {
        return condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

    static ValidationResult ifTrue(Supplier<Boolean> condition, String errorCode) {
        return condition.get()
                ? ValidationResult.from(ErrorDto.from(errorCode))
                : ValidationResult.success();

    }

    static ValidationResult ifFalse(Supplier<Boolean> condition, ErrorDto failureDto) {
        return !condition.get()
                ? ValidationResult.from(failureDto)
                : ValidationResult.success();

    }

}
