package com.example.demo.service;

import lombok.Getter;

import java.util.List;

@Getter
public class SuccessDto implements BaseDto {
    private BaseDto resultObj;
    private List<BaseDto> resultList;

    // No-Args Private Const
    private SuccessDto() {
    }

    static public SuccessDto of(final BaseDto resultObj) {
        final SuccessDto successDto = new SuccessDto();
        successDto.resultObj = resultObj;
        return successDto;
    }

    static public SuccessDto of(final List<BaseDto> resultList) {
        final SuccessDto successDto = new SuccessDto();
        successDto.resultList = resultList;
        return successDto;
    }
}
