package com.mitchell.exception;

public class ClaimNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClaimNotFoundException(String exception) {

		super(exception);
	}
}
