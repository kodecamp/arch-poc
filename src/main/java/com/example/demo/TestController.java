package com.example.demo;

import com.example.demo.controller.ResponseObj;
import com.example.demo.controller.ResponseProcessor;
import com.example.demo.service.PersonService;
import com.example.demo.service.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class TestController {

	@Autowired
	private final PersonService service;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseObj> get(final @PathVariable(required = true) String id) {
		final ServiceResult sr = service.getPersonById(id);
		return ResponseProcessor.process(sr);

	}

	@GetMapping("/created")
	public ResponseEntity<ResponseObj> create() {
		ServiceResult rs = service.getPeople();
		return ResponseProcessor.process(rs);
	}

	@GetMapping("/serverError")
	public ResponseEntity<ResponseObj> serverError() {
		return null;
	}

}
