package com.hcl.patient.model.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResponseWrapper<T> {

	private final boolean error;

	private final HttpStatus statusCode;

	private final T data;

}
