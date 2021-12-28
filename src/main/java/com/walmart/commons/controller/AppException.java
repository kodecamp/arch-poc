package com.walmart.commons.controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppException extends RuntimeException {
    private final String errorCode;
    private final Throwable cause;

    static public AppException from(String errorCode, Throwable cause) {
        return new AppException(errorCode,cause);

    }
}
