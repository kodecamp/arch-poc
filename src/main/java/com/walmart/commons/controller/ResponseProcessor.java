package com.walmart.commons.controller;

import com.walmart.commons.dto.BaseDto;
import com.walmart.commons.dto.FailureDto;
import com.walmart.commons.service.ServiceResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
* ResponseProcessor
*/
public final class ResponseProcessor {

	static public ResponseEntity ok(String key,Object obj) {
		return ResponseEntity.ok(
				SuccessResponse.instanceOf().add(key,obj));
	}

	static public ResponseEntity notFound(Object obj) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(SuccessResponse.instanceOf().add("error", obj));
	}

	static public ResponseEntity created(String key,Object obj) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(SuccessResponse.instanceOf().add(key,obj));
	}

	static public <T> ResponseEntity noContent(T obj) {
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(SuccessResponse.instanceOf().add("", ""));
	}

	static public ResponseEntity serverError(FailureDto obj) {
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(obj);
	}

	static public ResponseEntity authenticationError(FailureDto obj) {
		System.out.println("error object = " + obj);
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(obj);
	}

	static public ResponseEntity badRequest(FailureDto obj) {
		System.out.println("error object = " + obj);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(obj);
	}

	static public ResponseEntity badRequest(String errorCode, String optMessage) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(FailureDto.from(errorCode, optMessage));
	}

	static public ResponseEntity process(String key,final ServiceResult sr) {

		final ResponseEntity response;

		switch(sr.getStatus()) {

			case SUCCESS_SINGLE:
				Object dto = sr.getResultObj();
				response = ResponseProcessor.ok(key,dto);
				break;

			case SUCCESS_MULTIPLE:
				List<Object> listOfDto = (List<Object>) sr.getResultObj();
				response = ResponseProcessor.ok(key,listOfDto);
				break;

			case FAILURE:
				FailureDto failureDto = ((FailureDto)sr.getResultObj());
				response = ResponseProcessor.serverError(failureDto);
				break;

			default:
					throw new IllegalArgumentException("NO MATCHING CASE FOUND");
		}
		return response;
	}

	static public LinkedHashMap<String, Object> process(Pair...keyValues) {
		LinkedHashMap<String, Object> result = new LinkedHashMap<>();

		Arrays.stream(keyValues).forEach((keyValue) -> {

			result.put(
					keyValue.getKey(),
					!keyValue.getServiceResult().isFailure()
							?  keyValue.getServiceResult().getResultObj()
					        : "");
		});

		return result;
	}


}
