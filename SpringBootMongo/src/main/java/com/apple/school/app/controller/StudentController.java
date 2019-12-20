package com.apple.school.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;
import com.apple.school.app.dto.StudentDTO;
import com.apple.school.app.exceptions.StudentGradeException;
import com.apple.school.app.service.StudentService;

/**
 * 
 * @author 800469 : Laxminarayana
 *
 */

@RestController
@RequestMapping("student")
public class StudentController {

	private static final Logger log = LogManager.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public StudentRest create(@Valid @RequestBody StudentRequestModel studentDetails) throws Exception {

		StudentRest studentResp = new StudentRest();
		System.out.println("...");
		studentResp = studentService.createStudent(studentDetails);
		return studentResp;

	}

	@PutMapping(path = "{studentId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public StudentRest updateStudent(@PathVariable String studentId, @RequestBody StudentRequestModel studentDetails) {

		log.info("StudentController : updateStudent() : " + studentId);
		StudentRest studentUpdateRes = new StudentRest();
		studentUpdateRes = studentService.updateStudent(studentId, studentDetails);
		return studentUpdateRes;
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@RequestMapping(path = "/{age}/{grade}")
	public List<StudentRest> getStudentList(@PathVariable String age, @PathVariable String grade) {

		log.info("Search Params : Age : " + age + " Grade : " + grade);

		List<StudentRest> studentList = studentService.getStudentsByAgeAndGrade(age, grade);
		return studentList;
	}
}
