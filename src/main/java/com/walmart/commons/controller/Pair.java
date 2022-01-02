package com.walmart.commons.controller;


import com.walmart.commons.service.ServiceResponse;
import lombok.Getter;

@Getter
public class Pair {

    private final String key;
    private final ServiceResponse serviceResult;

    private Pair(String key, ServiceResponse sr) {
        this.key = key;
        this.serviceResult = sr;
    }

    static public Pair from (String key, ServiceResponse sr) {
        return new Pair(key, sr);
    }



}
