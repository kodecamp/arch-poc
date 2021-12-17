package com.example.demo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorObj {
	private ErrorCodes errorCode;

}
