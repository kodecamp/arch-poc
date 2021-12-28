package com.walmart.testusecase.dto;

import com.walmart.commons.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestDto implements BaseDto {
    private String someValue;
    private int someOtherValue;

    static public TestDto from(String someValue, int someOtherValue) {
       return new TestDto(someValue, someOtherValue);
    }

}
