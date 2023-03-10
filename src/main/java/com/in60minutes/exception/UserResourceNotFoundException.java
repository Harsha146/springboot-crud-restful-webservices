package com.in60minutes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserResourceNotFoundException(String message) {
		super(message);
	}
}
