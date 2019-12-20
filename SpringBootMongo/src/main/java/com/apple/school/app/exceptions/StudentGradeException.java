package com.apple.school.app.exceptions;

public class StudentGradeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1327666451971167103L;
	public String gradeException;
	
	public StudentGradeException(String exceptionMessage) {
		// TODO Auto-generated constructor stub
		super(exceptionMessage);
		this.gradeException = exceptionMessage;
		
	}

}
