package com.example.demo.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler {
	
	@ExceptionHandler(DepartmentNotFound.class)
	public ResponseEntity<Map<String, String>> handleDepartmentNotFound(DepartmentNotFound ex) {
		Map<String, String> error = new HashMap<>();
	    error.put("error", ex.getMessage());
	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

}
