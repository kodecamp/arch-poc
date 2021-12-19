package com.walmart.commons.service;

import com.walmart.commons.validator.ValidationResult;
import lombok.Getter;
import java.util.List;

@Getter
public class ServiceResult {
    private Status status;
    private BaseDto resultObj;

    // private constructor
    private ServiceResult() {}

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
        System.out.println("errorCode = " + errorCode + ", shortDes = " + shortDes + ", details = " + details);
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = FailureDto.from(errorCode,shortDes, details);
        List<Integer> numbers = List.of(1, 2, 3);
        System.out.println("numbers = " + numbers);
        return serviceResult;
    }

    static public ServiceResult withFailure(FailureDto failureDto) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = failureDto;
        return serviceResult;
    }

    static public ServiceResult withFailure(List<BaseDto> failures) {
        var serviceResult = new ServiceResult();
        serviceResult.status = Status.FAILURE;
        serviceResult.resultObj = (BaseDto) failures;
        return serviceResult;
    }

    static public ServiceResult processValidationResult(ValidationResult vr, BaseDto baseDto) {
       return vr.isSuccess() ? withSuccess(baseDto): withFailure(vr.error().get()) ;
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

    public static void main(String[] args) {
        ServiceResult.withSuccess(new BaseDto() {});
        ServiceResult serviceResult2 = ServiceResult.withSuccess(List.of(new BaseDto() {}));

    }

}
