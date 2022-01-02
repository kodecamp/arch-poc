package com.walmart.commons.service;

import com.walmart.commons.dto.ErrorDto;
import com.walmart.commons.validator.ValidationResult;
import lombok.Getter;

import java.util.List;

@Getter
public class ServiceResponse {
    private Status status;
    private Object resultObj;

    // Possible Status Codes
    public enum Status {
        SUCCESS_SINGLE,SUCCESS_MULTIPLE, FAILURE
    }

    // private constructor
    private ServiceResponse() {}

    ///////////////// Start : Overloaded static factory methods

    static public ServiceResponse withSuccess(Object resultObj) {
        ServiceResponse serviceResult = new ServiceResponse();
        serviceResult.status = Status.SUCCESS_SINGLE;
        serviceResult.resultObj = resultObj;
        return serviceResult;
    }

    static public ServiceResponse withSuccess(List<Object> resultList) {
        ServiceResponse serviceResult = new ServiceResponse();
        serviceResult.status = Status.SUCCESS_MULTIPLE;
        serviceResult.resultObj = resultList;
        return serviceResult;
    }

    static public ServiceResponse withFailure(String errorCode) {
        ServiceResponse serviceResult = new ServiceResponse();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = ErrorDto.from(errorCode);
        return serviceResult;
    }

    static public ServiceResponse withFailure(ErrorDto failureDto) {
        ServiceResponse serviceResult = new ServiceResponse();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = failureDto;
        return serviceResult;
    }

    static public ServiceResponse withFailure(List<Object> failures) {
        ServiceResponse serviceResult = new ServiceResponse();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj =  failures;
        return serviceResult;
    }

    static public ServiceResponse processValidationResult(ValidationResult vr, Object baseDto) {
       return vr.isSuccess()
               ? withSuccess(baseDto)
               : withFailure(vr.error().orElse(ErrorDto.empty())) ;
    }

    ///////////////// End : Overloaded static factory methods

    public boolean isSuccessSingle() {
        return this.status.equals(Status.SUCCESS_SINGLE);
    }
    public boolean isSuccessMultiple() {
        return this.status.equals(Status.SUCCESS_MULTIPLE);
    }
    public boolean isFailure() {
        return this.status.equals(Status.FAILURE);
    }

}
