package com.apple.school.app.dto;

public class StudentDTO {
	
	public String studentId;
	public String studentName;
	public String age;
	public String grade;
	
	
	  public String getstudentId() {
		  return studentId;
	  }
	  public void setstudentId(String studentId) {
		  this.studentId = studentId;
	  }	  
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
