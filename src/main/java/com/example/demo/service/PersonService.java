package com.example.demo.service;

import com.example.demo.person.PersonDto;

import java.util.Arrays;
import java.util.List;

/**
 * 1. -> SUCCESS with single result
 * 2. -> SUCCESS with multiple result
 * 3. -> FAILURE with error object
 *       1. -> Due to business validations' failure
 *       2. -> Logical Errors
 */
public class PersonService {

    public ServiceResult getPersonById(String id) {
        var personDto = PersonDto.builder().id("100").name("Sunil");

        return ServiceResult.withSuccess((BaseDto) personDto);

    }

    public ServiceResult getPeople(String name) {
        List<PersonDto> resultList = Arrays.asList(null, null, null);
        return ServiceResult.withSuccess((BaseDto) resultList);

    }

    public ServiceResult getPersonWithError(String name) {
        return ServiceResult.withFailure("","", "");
    }

    public ServiceResult getPeople() {
        return null;
    }
}
