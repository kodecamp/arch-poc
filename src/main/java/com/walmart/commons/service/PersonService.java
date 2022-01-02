package com.walmart.commons.service;

import com.walmart.commons.dto.BaseDto;
import com.walmart.persondetails.PersonDto;
import com.walmart.persondetails.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 1. -> SUCCESS with single result
 * 2. -> SUCCESS with multiple result
 * 3. -> FAILURE with error object
 *       1. -> Due to business validations' failure
 *       2. -> Logical Errors
 */
@Service
public class PersonService {


    @Autowired
    TestService service;

    public ServiceResponse getPersonById(String id) {
        System.out.println("------------------ getPersonById");
        var personDto = PersonDto.from("100","John", 37);
        ServiceResponse sr1= service.getPersonById("5");
        ServiceResponse sr2= service.getAllPeople();
        return ServiceResponse.withSuccess((BaseDto) personDto);

    }

    /**
     *
     * @return <tt>List of <tt>PersonDto</tt></tt>
     */
    public ServiceResponse getPeople() {
        System.out.println("------------------ getPeople");
        List<BaseDto> resultList = Arrays.asList(
                PersonDto.from("100","John", 37),
                PersonDto.from("101","Jacob", 38),
                PersonDto.from("102","Sachin", 39));
        return ServiceResponse.withSuccess(resultList);
    }

    public ServiceResponse getPersonWithError(String name) {
        return ServiceResponse.withFailure("");
    }

    public ServiceResponse getPeople(String name) {
        return null;
    }
}
