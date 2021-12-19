package com.walmart.commons.validator;

import com.walmart.commons.service.BaseDto;
import com.walmart.commons.service.FailureDto;
import com.walmart.testusecase.PersonDto;

import java.util.*;
import java.util.function.Function;

public class ValidationResult {

    private final List<FailureDto> validationErrors = new ArrayList<>();
    private final ValidationStatus status;
    private List<Validator<BaseDto>> validators = new ArrayList<>();

    private ValidationResult(List<FailureDto> errors) {
        this.validationErrors.addAll(errors);
        this.status = ValidationStatus.VALIDATION_FAILURE;
    }

    private ValidationResult() {
        this.status = ValidationStatus.VALIDATION_SUCCESS;
    }

    private ValidationResult(FailureDto error) {
        this.validationErrors.add(error);
        this.status = ValidationStatus.VALIDATION_FAILURE;
    }

    private ValidationResult(ValidationStatus status) {
        this.status = status;
    }

    static public ValidationResult from(FailureDto dto) {
        return new ValidationResult(dto);
    }

    static public ValidationResult success() {
        return new ValidationResult(ValidationStatus.VALIDATION_SUCCESS);
    }

    static public ValidationResult from(List<FailureDto> listOfDto) {
        return new ValidationResult(listOfDto);
    }

    public final List<FailureDto> errors() {
        return Collections.unmodifiableList(validationErrors);
    }

    public final Optional<FailureDto> error() {
        return this.validationErrors.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(this.validationErrors.get(0));
    }

    public boolean isSuccess() {
        return this.status.equals(ValidationStatus.VALIDATION_SUCCESS);
    }

    public ValidationResult andThen(Validator<BaseDto> validator) {
        this.validators.add(validator);
        return this;
    }



}
