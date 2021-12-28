package com.walmart.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessDto implements BaseDto {
    private BaseDto resultObj;
    private List<BaseDto> resultList;

    // No-Args Private Const
    private SuccessDto() {
    }

    static public BaseDto of(final BaseDto resultObj) {
        final SuccessDto successDto = new SuccessDto();
        successDto.resultObj = resultObj;
        return successDto;
    }

    static public BaseDto of(final List<BaseDto> resultList) {
        final SuccessDto successDto = new SuccessDto();
        successDto.resultList = resultList;
        return successDto;
    }
}
