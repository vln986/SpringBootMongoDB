package com.apple.school.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;
import com.apple.school.app.dao.StudentDao;
import com.apple.school.app.exceptions.StudentGradeException;
import com.apple.school.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao studentDao;

	
	public StudentRest createStudent(StudentRequestModel studentDetails) {
		
		StudentRest student = studentDao.createStudent(studentDetails);
		
		return student;
	}
 
	@Override
	public StudentRest updateStudent(String studentId, StudentRequestModel studentDetails) {
		StudentRest studentUpdateRest = studentDao.updateStudent(studentId, studentDetails);
		
		return studentUpdateRest;
	}

	@Override
	public List<StudentRest> getStudentsByAgeAndGrade(String age, String grade) {
		// TODO Auto-generated method stub
		
		List<StudentRest> studentListRest = studentDao.getStudentListByAgeAndGrade(age, grade);
		return studentListRest;
	}
	
	

}
