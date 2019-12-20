package com.apple.school.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;
import com.apple.school.app.controller.StudentController;
import com.apple.school.app.service.impl.StudentServiceImpl;
import com.apple.school.app.util.StudentConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongoApplicationTests {
	
	private static final Logger log = LogManager.getLogger(SpringBootMongoApplicationTests.class);
	
	@Autowired
	StudentController studentController;

	StudentRest studentRest = null;
	StudentRequestModel studentCreateReq = null;
	StudentRequestModel studentUpdateReq = null;
	@Autowired
	StudentServiceImpl imple;
	
	@Before
	public void setUp() throws Exception {
		
		studentCreateReq = new StudentRequestModel();
		studentCreateReq.setStudentName(StudentConstants.STUDENT_NAME);
		studentCreateReq.setAge(StudentConstants.STUDENT_AGE_INT);
		studentCreateReq.setGrade(StudentConstants.GRADE_A);
		
		studentUpdateReq = new StudentRequestModel();
		
		studentUpdateReq.setStudentName(StudentConstants.STUDENT_NAME);
		studentUpdateReq.setAge(StudentConstants.STUDENT_AGE_INT);
		studentUpdateReq.setGrade(StudentConstants.GRADE_B);
		
		
	
	} 
	@Test
	public void testCreateStudent() {
			
		studentRest = new StudentRest();
	 	try {
			studentRest = studentController.create(studentCreateReq);
			assertNotNull(studentRest);
			assertEquals(studentCreateReq.getStudentName(), studentRest.getStudentName());
			assertEquals(studentCreateReq.getAge(), studentRest.getAge());
			assertEquals(studentCreateReq.getGrade(), studentRest.getGrade());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e);
		}
	}
	@Test
	public void testUpdateStudent() {
		
		String studentId = studentUpdateReq.getAge() + studentUpdateReq.getGrade() + studentUpdateReq.getStudentName().charAt(0);
		
		studentRest = new StudentRest();
		studentRest = studentController.updateStudent(studentId, studentUpdateReq);
		assertNotNull(studentRest);
		assertEquals(studentUpdateReq.getStudentName(), studentRest.getStudentName());
		assertEquals(studentUpdateReq.getAge(), studentRest.getAge());
		assertEquals(studentUpdateReq.getGrade(), studentRest.getGrade());
		
	}
	@Test
	public void testSearchStudent() {
		List<StudentRest> studentList = new ArrayList<StudentRest>();
		
		for(int i =1;i<=5;i++) {
			StudentRest studentRest = new StudentRest();
			studentRest.setStudentName(StudentConstants.STUDENT_NAME+i);
			studentRest.setAge(StudentConstants.STUDENT_AGE_INT);
			studentRest.setGrade(StudentConstants.GRADE_C);
			String studentId = studentRest.getAge() + studentRest.getGrade() + studentRest.getStudentName().charAt(0);
			studentRest.setstudentId(studentId);
			studentList.add(studentRest);
			
		}
		List<StudentRest> studentListResp = studentController.getStudentList(StudentConstants.STUDENT_AGE_INT, StudentConstants.GRADE_A);
		assertNotNull(studentListResp);
	}
}
