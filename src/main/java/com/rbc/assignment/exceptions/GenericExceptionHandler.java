package com.rbc.assignment.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.rbc.assignment.response.CustomerProfileResponse;

/**
 * Exception Handler
 * 
 * @author Sandip Nirmal
 *
 */
@ControllerAdvice
public class GenericExceptionHandler {

	@Value("${messages.failure.generic.message}")
	private String failureMessage;

	@Value("${messages.failure.status}")
	private String failureStatus;

	/**
	 * Exception Handler for exception {@link CustomerProfileException}
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(CustomerProfileException.class)
	public final ResponseEntity<CustomerProfileResponse> handleCustomerProfileException(
			CustomerProfileException ex, WebRequest request) {
		CustomerProfileResponse errorDetails = new CustomerProfileResponse(ex.getMessage(),
				failureStatus);
		return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
	}

}
