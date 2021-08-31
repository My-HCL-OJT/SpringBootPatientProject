package com.hcl.patient.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patient.model.response.ExceptionResponse;
import com.hcl.patient.model.response.ResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ErrorControllerImpl implements ErrorController {
	@GetMapping("/error")
	public ResponseWrapper<ExceptionResponse> handleError(HttpServletRequest request, HttpServletResponse response) {
		String reason = (String) request.getAttribute("javax.servlet.error.message");
		int status = response.getStatus();

		log.info("Error occurred: " + reason);
		return new ResponseWrapper<>(true, HttpStatus.valueOf(status), new ExceptionResponse(new Date(), reason));
	}
}
