package com.example.demo.service;


import lombok.Getter;

import java.util.List;

@Getter
public class ServiceResult {
    private Status status;
    private BaseDto resultObj;

    static public ServiceResult withSuccess(BaseDto resultObj) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.SUCCESS_SINGLE;
        serviceResult.resultObj = SuccessDto.of(resultObj);
        return serviceResult;
    }

    static public ServiceResult withSuccess(List<BaseDto> resultList) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.SUCCESS_MULTIPLE;
        serviceResult.resultObj = SuccessDto.of(resultList);
        return serviceResult;
    }

    static public ServiceResult withFailure(String errorCode, String shortDes, String details) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = FailureDto.builder()
                .errorCode(errorCode)
                .shortMessage(shortDes)
                .details(details).build();
        return serviceResult;
    }

    static public ServiceResult withFailure(List<BaseDto> failures) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = (BaseDto) failures;
        return serviceResult;
    }

    public boolean isSuccessSingle() {
        return this.status.equals(Status.SUCCESS_SINGLE);
    }

    public boolean isSuccessMultiple() {
        return this.status.equals(Status.SUCCESS_MULTIPLE);
    }

    public boolean isFailure() {
        return this.status.equals(Status.FAILURE);
    }

    public static enum Status {
        SUCCESS_SINGLE,SUCCESS_MULTIPLE, FAILURE;
    }

}
