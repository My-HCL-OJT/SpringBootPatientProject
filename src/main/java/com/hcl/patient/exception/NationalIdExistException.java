package com.hcl.patient.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NationalIdExistException extends RuntimeException {

	private static final long serialVersionUID = -8631920993687636110L;

	public NationalIdExistException(String message) {
		super(message);
		log.warn(message);
	}
}
