package com.walmart.commons.controller;

import com.walmart.commons.dto.ErrorDto;
import com.walmart.commons.service.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
*  <p>
 *      Utility class for processing ServiceResponse
*  </p>
 *
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

	static public ResponseEntity serverError(ErrorDto obj) {
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(obj);
	}

	static public ResponseEntity authenticationError(ErrorDto obj) {
		System.out.println("error object = " + obj);
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(obj);
	}

	static public ResponseEntity badRequest(ErrorDto obj) {
		System.out.println("error object = " + obj);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(obj);
	}

	static public ResponseEntity badRequest(String errorCode, String optMessage) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ErrorDto.from(errorCode, optMessage));
	}

	static public ResponseEntity process(String key,final ServiceResponse sr) {

		final ResponseEntity response;

		switch(sr.getStatus()) {

			case SUCCESS_SINGLE:
				Object dto = sr.getResultObj();
				response = dto == null ? ResponseProcessor.noContent(dto)
				: ResponseProcessor.ok(key, dto);

				break;

			case SUCCESS_MULTIPLE:
				List<Object> listOfDto = (List<Object>) sr.getResultObj();

				response = listOfDto == null || listOfDto.isEmpty()
						? ResponseProcessor.noContent(listOfDto)
						: ResponseProcessor.ok(key, listOfDto);
//				response = ResponseProcessor.ok(key,listOfDto);
				break;

			case FAILURE:
				ErrorDto failureDto = ((ErrorDto)sr.getResultObj());
				response = ResponseProcessor.badRequest(failureDto);
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
