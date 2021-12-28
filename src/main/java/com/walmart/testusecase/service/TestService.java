package com.walmart.testusecase.service;

import com.walmart.commons.dto.FailureDto;
import com.walmart.commons.service.ServiceResult;
import com.walmart.commons.validator.*;
import com.walmart.testusecase.PersonDto;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    static private final Validator ageValidator1 = (baseDto) -> {
        System.out.println("-> AgeValidator 1");
        PersonDto person = (PersonDto) baseDto;
        return Validator.ifTrue(() -> person.getAge() < 0, "Age is less than Zero");
    };

    static private final Validator ageValidator2 = (baseDto) -> {
        System.out.println("-> AgeValidator 2");
        PersonDto person = (PersonDto) baseDto;
        return Validator.ifTrue(() -> person.getAge() > 100, FailureDto.from("500"));
    };


    public ServiceResult getPersonById(String id) {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        String rs  = RuleSet
                .of(rule1(person), rule2(person))
                .orElse("");

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        return ServiceResult.processValidationResult(vr, person);

    }

    public ServiceResult getAllPeople() {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        String rs  = RuleSet
                .of(rule1(person), rule2(person))
                .orElse("");

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        return ServiceResult.processValidationResult(vr, person);

    }

    private Rule<String> rule1(PersonDto person) {
        return Rule.is(
                () -> person.getAge() > 50 && person.getAge() < 60,
                () -> person.getName().toLowerCase());
    }

    private Rule<String> rule2(PersonDto person) {
        return Rule.is(
                () -> person.getAge() > 0,
                () -> person.getName().toUpperCase());
    }

    public ServiceResult savePerson(PersonDto pDto) {
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

}
