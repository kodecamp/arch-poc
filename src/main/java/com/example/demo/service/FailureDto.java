package com.example.demo.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FailureDto implements BaseDto {
    private final String errorCode;
    private final String shortMessage;
    private final String details;
}
