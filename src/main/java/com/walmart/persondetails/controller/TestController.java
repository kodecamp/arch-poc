package com.walmart.persondetails.controller;

import com.walmart.commons.controller.*;
import com.walmart.commons.dto.BaseDto;
import com.walmart.commons.dto.ErrorDto;
import com.walmart.commons.service.PersonService;
import com.walmart.commons.service.ServiceResponse;
import com.walmart.persondetails.PersonDto;
import com.walmart.persondetails.dto.TestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class TestController {

	@Autowired
	private final PersonService service;

	@GetMapping("")
//	@EnableAuthentication
	public ResponseEntity getAllPerson() {
		final ServiceResponse sr = service.getPeople();
//		throw AppException.from("500", new Exception() );
//		return ResponseProcessor.process("all", sr);
		return ResponseProcessor.badRequest(
				ErrorDto.from("500", "100")
		);
//		ResponseObj responseObj = ResponseObj.
	}

	@GetMapping("/{id}")
	@EnableAuthentication
	public ResponseEntity getPersonBy(
			final @PathVariable(required = true) String id) {
		final ServiceResponse sr = service.getPersonById(id);
		return ResponseProcessor.process("person",sr);
	}

	@PostMapping("/new")
	@EnableAuthentication
	public ResponseEntity create(@RequestBody PersonDto personDto) {
		ServiceResponse rs = service.getPeople();
		return ResponseProcessor.process("all",rs);
	}

	@GetMapping("/test")
	@EnableAuthentication
	public LinkedHashMap<String, Object> test() {
		LinkedHashMap<String, BaseDto> map = new LinkedHashMap<>();
		final ServiceResponse all = service.getPeople();
		final ServiceResponse one = service.getPersonById("");
		final ServiceResponse third = ServiceResponse.withSuccess(
				TestDto.from("hello", 100));
		final ServiceResponse fourth = ServiceResponse.withFailure("1000");
		return ResponseProcessor.process(
				Pair.from("all", all),
				Pair.from("one", one),
				Pair.from("third", third),
				Pair.from("fourth", fourth)
		);

	}

	@GetMapping("/serverError")
	public ResponseEntity<ResponseObj> serverError() {
		return null;
	}

}
