package com.example.demo.controller;

import com.example.demo.controller.ResponseObj;
import com.example.demo.service.FailureDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* ErrorResponse
*/
@Builder
@Getter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public final class ErrorResponse implements ResponseObj {
	private String shortMessage;
	private String errorCode;
	private String desc;

	static public ErrorResponse from(FailureDto dto) {
		return new ErrorResponse(dto.getShortMessage(), dto.getErrorCode(), dto.getDetails());
	}
}
