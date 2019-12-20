package com.apple.school.app.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.apple.school.app.Response.ErrorMessage;


@ControllerAdvice
public class AppleAppExceptionHandler {
	
	@ExceptionHandler(value = {StudentGradeException.class})
	public ResponseEntity<Object> handleStudentGradeException(StudentGradeException ex, WebRequest request){
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
