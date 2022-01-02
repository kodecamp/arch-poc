package com.walmart.commons.aspects;

import com.walmart.commons.controller.AppException;
import com.walmart.commons.controller.AuthenticationException;
import com.walmart.commons.controller.ResponseProcessor;
import com.walmart.commons.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerErrorInterceptor extends ResponseEntityExceptionHandler {

    // this will handle Authentication level exceptions
    // should be thrown at controller level
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException exception
            , WebRequest request) {
        System.out.println("-----------------++++++-------------->");
        log.error(exception.getMsg());
        exception.printStackTrace();
        return ResponseProcessor.authenticationError(
                        ErrorDto.from("400", "Invalid User"));
    }

    // will handle custom excpetions of the app level -> if you are fond of playing with exceptions
    // can be thrown from anywhere
    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException exception
            , WebRequest request) {
        System.out.println("App Exception Occurred");
        // for developers to debug
        exception.getCause().printStackTrace();
        return ResponseProcessor.serverError(
                        ErrorDto.from(exception.getErrorCode(), "Something went wrong"));
    }

    // will handle the all jpa level exceptions
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity handleDataAccessException(DataAccessException exception
            , WebRequest request){
        System.out.println("App Exception Occurred");
        // for developers to debug
        exception.getCause().printStackTrace();
        return ResponseProcessor.serverError(
                ErrorDto.from("500", "Something went wrong"));
    }
}
