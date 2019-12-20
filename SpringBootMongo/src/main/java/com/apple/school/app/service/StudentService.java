package com.apple.school.app.service;

import java.util.List;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;

public interface StudentService {
	
	public StudentRest createStudent(StudentRequestModel studentDetails);
	
	public StudentRest updateStudent(String studentId, StudentRequestModel studentDetails);
	
	public List<StudentRest> getStudentsByAgeAndGrade(String age, String grade);

}
