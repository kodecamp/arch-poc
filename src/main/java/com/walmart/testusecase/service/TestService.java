package com.walmart.testusecase.service;

import com.walmart.commons.service.BaseDto;
import com.walmart.commons.service.FailureDto;
import com.walmart.commons.service.ServiceResult;
import com.walmart.commons.validator.*;
import com.walmart.testusecase.PersonDto;
import org.springframework.stereotype.Service;



@Service
public class TestService {


    static private final Validator<BaseDto> ageValidator1 = (baseDto) -> {
        System.out.println("-> AgeValidator 1");
        PersonDto person = (PersonDto) baseDto;
        return Validator.ifTrue(() -> person.getAge() < 0, FailureDto.from("100"));
    };

    static private final Validator<BaseDto> ageValidator2 = (baseDto) -> {
        System.out.println("-> AgeValidator 2");
        PersonDto person = (PersonDto) baseDto;
        return Validator.ifTrue(() -> person.getAge() > 100, FailureDto.from("500"));
    };


    public ServiceResult get(String id) {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        String rs  = RuleSet
                .create(getRule1(person), getRule2(person))
                .runWithDefault("");

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        return ServiceResult.processValidationResult(vr, person);

    }

    private Rule<String> getRule1(PersonDto person) {
        return Rule.from(
                () -> person.getAge() > 50 && person.getAge() < 60,
                () -> person.getName().toLowerCase());
    }

    private Rule<String> getRule2(PersonDto person) {
        return Rule.from(
                () -> person.getAge() > 0,
                () -> person.getName().toUpperCase());
    }

    public ServiceResult save(PersonDto pDto) {
        return null;
    }

    public static void main(String[] args) {
        PersonDto person = PersonDto.from("100", "Sunil",37);
        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        ServiceResult.processValidationResult(vr, person);

    }

    static void test(int value) {
    }


}
