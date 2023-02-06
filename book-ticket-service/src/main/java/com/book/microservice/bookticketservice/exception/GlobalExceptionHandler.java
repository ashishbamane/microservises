package com.book.microservice.bookticketservice.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.book.microservice.bookticketservice.models.DataResponse;
import com.book.microservice.bookticketservice.models.ErrorDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EventNotFoundException.class)
	public ResponseEntity<Object> handleEventNotFoundException(EventNotFoundException ex, WebRequest request) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		errorList.add(errorDetails);

		DataResponse dataResponse = new DataResponse(null, null, errorList, null);
		return new ResponseEntity<>(dataResponse, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
		

		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		

		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		//errors.add(errors);

		DataResponse dataResponse = new DataResponse(null, null, errorList, errors);
		return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);

		// return new ResponseEntity<>(body, headers, status);

	}

	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "");
		 errorList.add(errorDetails);
		DataResponse dataResponse = new DataResponse(null, null, errorList, null);
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(dataResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dataResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	/*
	 * @ExceptionHandler(NoDataFoundException.class) public ResponseEntity<Object>
	 * handleNodataFoundException( NoDataFoundException ex, WebRequest request) {
	 * 
	 * Map<String, Object> body = new LinkedHashMap<>(); body.put("timestamp",
	 * LocalDateTime.now()); body.put("message", "No cities found");
	 * 
	 * return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); }
	 * 
	 * @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(
	 * MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	 * WebRequest request) {
	 * 
	 * Map<String, Object> body = new LinkedHashMap<>(); body.put("timestamp",
	 * LocalDate.now()); body.put("status", status.value());
	 * 
	 * List<String> errors = ex.getBindingResult() .getFieldErrors() .stream()
	 * .map(x -> x.getDefaultMessage()) .collect(Collectors.toList());
	 * 
	 * body.put("errors", errors);
	 * 
	 * return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); }
	 */
}