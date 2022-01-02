package com.walmart.commons.validator;

import com.walmart.commons.dto.ErrorDto;

import java.util.*;

public class ValidationResult {

    private final List<ErrorDto> validationErrors = new ArrayList<>();
    private final ValidationStatus status;
    private final List<Validator> validators = new ArrayList<>();

    private ValidationResult(List<ErrorDto> errors) {
        this.validationErrors.addAll(errors);
        this.status = ValidationStatus.VALIDATION_FAILURE;
    }

    private ValidationResult() {
        this.status = ValidationStatus.VALIDATION_SUCCESS;
    }

    private ValidationResult(ErrorDto error) {
        this.validationErrors.add(error);
        this.status = ValidationStatus.VALIDATION_FAILURE;
    }

    private ValidationResult(ValidationStatus status) {
        this.status = status;
    }

    static public ValidationResult from(ErrorDto dto) {
        return new ValidationResult(dto);
    }

    static public ValidationResult success() {
        return new ValidationResult(ValidationStatus.VALIDATION_SUCCESS);
    }

    static public ValidationResult from(List<ErrorDto> listOfDto) {
        return new ValidationResult(listOfDto);
    }

    public final List<ErrorDto> errors() {
        return Collections.unmodifiableList(validationErrors);
    }

    public final Optional<ErrorDto> error() {
        return this.validationErrors.isEmpty()
                ? Optional.empty()
                : Optional.ofNullable(this.validationErrors.get(0));
    }

    public boolean isSuccess() {
        return this.status.equals(ValidationStatus.VALIDATION_SUCCESS);
    }

    public ValidationResult andThen(Validator validator) {
        this.validators.add(validator);
        return this;
    }
}
