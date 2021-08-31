package com.hcl.patient.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NameAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 6212380038966629489L;

	public NameAlreadyExistException(String message) {
		super(message);
		log.warn(message);
	}
}
