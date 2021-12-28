package com.walmart.commons.controller;


import com.walmart.commons.service.ServiceResult;
import lombok.Getter;

@Getter
public class Pair {

    private final String key;
    private final ServiceResult serviceResult;

    private Pair(String key, ServiceResult sr) {
        this.key = key;
        this.serviceResult = sr;
    }

    static public Pair from (String key, ServiceResult sr) {
        return new Pair(key, sr);
    }



}
