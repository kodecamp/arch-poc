package com.walmart.commons.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.walmart.commons.dto.BaseDto;
import com.walmart.commons.dto.FailureDto;
import com.walmart.commons.validator.ValidationResult;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResult {
    private Status status;
    private Object resultObj;

    // private constructor
    private ServiceResult() {}

    static public ServiceResult withSuccess(Object resultObj) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.status = Status.SUCCESS_SINGLE;
        serviceResult.resultObj = resultObj;
        return serviceResult;
    }

    static public ServiceResult withSuccess(List<Object> resultList) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.status = Status.SUCCESS_MULTIPLE;
        serviceResult.resultObj = resultList;
        return serviceResult;
    }

    static public ServiceResult withFailure(String errorCode) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = FailureDto.from(errorCode);
        return serviceResult;
    }

    static public ServiceResult withFailure(FailureDto failureDto) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = failureDto;
        return serviceResult;
    }

    static public ServiceResult withFailure(List<Object> failures) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj =  failures;
        return serviceResult;
    }

    static public ServiceResult processValidationResult(ValidationResult vr, Object baseDto) {
       return vr.isSuccess()
               ? withSuccess(baseDto)
               : withFailure(vr.error().orElse(FailureDto.empty())) ;
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

    public enum Status {
        SUCCESS_SINGLE,SUCCESS_MULTIPLE, FAILURE
    }

    public static void main(String[] args) {
        ServiceResult.withSuccess(new BaseDto() {});
        ServiceResult serviceResult2 = ServiceResult.withSuccess(Collections.singletonList(new BaseDto() {
        }));

    }

}
