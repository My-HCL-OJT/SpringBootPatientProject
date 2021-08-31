package com.hcl.patient.config.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.patient.exception.NameAlreadyExistException;
import com.hcl.patient.exception.NationalIdExistException;
import com.hcl.patient.model.response.ExceptionResponse;
import com.hcl.patient.model.response.ResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(NameAlreadyExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseWrapper<ExceptionResponse> handleUsernameFoundException(NameAlreadyExistException ex) {
		log.info("Error occurred: " + ex.getLocalizedMessage());
		return new ResponseWrapper<>(true, HttpStatus.BAD_REQUEST, new ExceptionResponse(new Date(), ex.getLocalizedMessage()));
	}

	@ExceptionHandler(NationalIdExistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseWrapper<ExceptionResponse> handleNationalIdFoundException(NationalIdExistException ex) {
		log.info("Error occurred: " + ex.getLocalizedMessage());
		return new ResponseWrapper<>(true, HttpStatus.BAD_REQUEST, new ExceptionResponse(new Date(), ex.getLocalizedMessage()));
	}

}
