package com.walmart.commons.controller;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorObj {
	private ErrorCodes errorCode;

}
