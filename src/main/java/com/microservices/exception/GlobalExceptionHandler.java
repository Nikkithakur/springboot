package com.microservices.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { AccountNotPresentException.class, AccountDeletionException.class })
	public CustomErrorMessage accountException(Exception exception) {
		return CustomErrorMessage.builder().errorMessage(exception.getMessage())
				.errorStatusCode(HttpStatus.NO_CONTENT.toString())
				.errorTimeStamp(LocalDateTime.now(ZoneOffset.UTC).toString()).build();
	}

}
