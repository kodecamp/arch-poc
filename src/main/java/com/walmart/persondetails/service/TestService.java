package com.walmart.persondetails.service;

import com.walmart.commons.dto.ErrorDto;
import com.walmart.commons.service.ServiceResponse;
import com.walmart.commons.validator.*;
import com.walmart.persondetails.PersonDto;
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
        return Validator.ifTrue(() -> person.getAge() > 100, ErrorDto.from("500"));
    };


    public ServiceResponse getPersonById(String id) {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        String rs  = RuleSet
                .of(rule1(person), rule2(person))
                .orElse("");

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        return ServiceResponse.processValidationResult(vr, person);

    }

    public ServiceResponse getAllPeople() {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        String rs  = RuleSet
                .of(rule1(person), rule2(person))
                .orElse("");

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        return ServiceResponse.processValidationResult(vr, person);

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

    public ServiceResponse savePerson(PersonDto pDto) {
        return null;
    }

    public static void main(String[] args) {
        PersonDto person = PersonDto.from("100", "Sunil",37);

        final ValidationResult vr = ValidationContext.build()
                .andThen(ageValidator1)
                .andThen(ageValidator2)
                .validate(person);

        ServiceResponse.processValidationResult(vr, person);
    }

}
