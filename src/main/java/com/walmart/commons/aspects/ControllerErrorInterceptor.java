package com.walmart.commons.aspects;

import com.walmart.commons.controller.AppException;
import com.walmart.commons.controller.AuthenticationException;
import com.walmart.commons.controller.ResponseProcessor;
import com.walmart.commons.dto.FailureDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerErrorInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException exception
            , WebRequest request) {
        System.out.println("-----------------++++++-------------->");
        return ResponseProcessor.authenticationError(
                        FailureDto.from("400", "Invalid User"));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException exception
            , WebRequest request) {
        System.out.println("App Exception Occurred");
        // for developers to debug
        exception.getCause().printStackTrace();
        return ResponseProcessor.serverError(
                        FailureDto.from(exception.getErrorCode(), "Something went wrong"));
    }
}
