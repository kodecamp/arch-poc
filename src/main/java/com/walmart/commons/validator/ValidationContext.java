package com.walmart.commons.validator;

import com.walmart.commons.dto.BaseDto;

import java.util.ArrayList;
import java.util.List;

public final class ValidationContext {
    private final List<Validator> validators;

    private ValidationContext() {
        this.validators = new ArrayList<>();
    }

    public static ValidationContext build() {
        return new ValidationContext();
    }

    public ValidationContext andThen(Validator validator) {
        this.validators.add(validator);
        return this;
    }

    public ValidationResult validate(BaseDto baseDto) {
        System.out.println("baseDto = " + baseDto);
        ValidationResult vr = ValidationResult.success();
        System.out.println("---------> validators : " + this.validators);
        for(Validator validator : this.validators) {
            vr = validator.validate(baseDto);
            if(!vr.isSuccess()) {

                return vr;
            }

        }
        return vr;
    }






}
