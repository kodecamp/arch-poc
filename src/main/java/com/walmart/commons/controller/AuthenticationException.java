package com.walmart.commons.controller;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
    private String msg;

    public AuthenticationException(String msg) {
        this.msg = msg;
    }


}
