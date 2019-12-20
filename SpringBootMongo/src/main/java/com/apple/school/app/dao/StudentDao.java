package com.apple.school.app.dao;

import java.util.List;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;

public interface StudentDao {
	
	public StudentRest createStudent(StudentRequestModel studentDetails);
	
	public StudentRest updateStudent(String studentId, StudentRequestModel studentDetails);
	
	public List<StudentRest> getStudentListByAgeAndGrade(String age, String grade);

}
