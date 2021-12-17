package com.example.demo.service;

import java.util.*;

public class ValidationResult {

    private final List<FailureDto> validationErrors = new ArrayList<>();

    private ValidationResult(List<FailureDto> errors) {
        this.validationErrors.addAll(errors);
    }

    private ValidationResult(FailureDto error) {
        this.validationErrors.add(error);
    }

    static public ValidationResult from(FailureDto dto) {
        return new ValidationResult(dto);
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
                : Optional.ofNullable(this.validationErrors.get(1));
    }

}
