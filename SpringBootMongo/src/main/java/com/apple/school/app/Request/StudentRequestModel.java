package com.apple.school.app.Request;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.apple.school.app.annotations.StudentGrade;
import com.apple.school.app.util.StudentConstants;

public class StudentRequestModel {
	
	@NotEmpty(message = StudentConstants.STUDENT_NAME_EMPTY)
	public String studentName;
	@NotEmpty(message = StudentConstants.STUDENT_AGE_EMPTY)
	public String age;
	@StudentGrade(message = StudentConstants.GRADE_SHOULD_BE_A_B)
	public String grade;
	
	
	
	  public String getStudentName() {
		  return studentName;
	  }
	  public void setStudentName(String studentName) {
		  this.studentName = studentName;
	  }
	  
	  public String getAge() {
		  return age;
	  }
	  
	  public void setAge(String age) {
		  this.age = age;
	  }
	  
	  public String getGrade() {
		  return grade;
		  
	  }
	  public void setGrade(String grade) {
		  this.grade = grade;
	  }

}
