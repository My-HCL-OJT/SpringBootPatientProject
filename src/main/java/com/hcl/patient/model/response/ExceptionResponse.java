package com.hcl.patient.model.response;

import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionResponse {

	private final int error = 1;

	private final Date timestamp;

	private final String reason;

}