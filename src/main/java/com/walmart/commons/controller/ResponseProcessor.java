package com.walmart.commons.controller;

import com.walmart.commons.service.BaseDto;
import com.walmart.commons.service.FailureDto;
import com.walmart.commons.service.ServiceResult;
import com.walmart.commons.service.SuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
* ResponseProcessor
*/
public final class ResponseProcessor {

	static public final <T> ResponseEntity<ResponseObj> ok(T obj) {
		return ResponseEntity.ok(SuccessResponse.builder().responseValue(obj).build());
	}

	static public final <T> ResponseEntity<ResponseObj> notFound(T obj) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(SuccessResponse.builder().responseValue(obj).build());
	}

	static public final <T> ResponseEntity<ResponseObj> created(T obj) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(SuccessResponse.builder().responseValue(obj).build());
	}

	static public final <T> ResponseEntity<ResponseObj> noContent(T obj) {
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT)
			.body(SuccessResponse.builder().responseValue(obj).build());
	}

	static public final ResponseEntity<ResponseObj> serverError(ErrorResponse obj) {
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(obj);
	}

	static public ResponseEntity<ResponseObj> process(final ServiceResult sr) {

		final ResponseEntity<ResponseObj> response;

		switch(sr.getStatus()) {
			case SUCCESS_SINGLE:
				BaseDto dto = ((SuccessDto)sr.getResultObj()).getResultObj();
				response = ResponseProcessor.ok(dto);
				break;

			case SUCCESS_MULTIPLE:
				List<BaseDto> listOfDto = ((SuccessDto)sr.getResultObj()).getResultList();
				response = ResponseProcessor.ok(listOfDto);
				break;

			case FAILURE:
				FailureDto failureDto = ((FailureDto)sr.getResultObj());
				response = ResponseProcessor.serverError(ErrorResponse.from(failureDto));
				break;

			default:
					throw new IllegalArgumentException("NO MATCHING CASE FOUND");
		}
		return response;
	}


}
