package com.hcl.patient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
	@GetMapping
	public ResponseEntity<String> helloWorld() {
		ResponseEntity<String> re = new ResponseEntity<String>("Hello World", HttpStatus.OK);
		return re;
	}
}
