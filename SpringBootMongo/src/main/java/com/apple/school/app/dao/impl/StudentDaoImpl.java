package com.apple.school.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.apple.school.app.Request.StudentRequestModel;
import com.apple.school.app.Response.StudentRest;
import com.apple.school.app.dao.StudentDao;
import com.apple.school.app.dto.StudentDTO;
import com.apple.school.app.exceptions.StudentGradeException;
import com.apple.school.app.util.StudentConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class StudentDaoImpl implements StudentDao {

	private static final Logger log = LogManager.getLogger(StudentDaoImpl.class);

	public StudentRest createStudent(StudentRequestModel studentDetails) {

		if (studentDetails.getGrade().equalsIgnoreCase(StudentConstants.GRADE_C)) {
			log.info(StudentConstants.STUDENT_GRADE_EXCEPTION);
			throw new StudentGradeException(StudentConstants.IMPROVE_SKILLS);
		} 

		StudentDTO studentDTO = new StudentDTO();

		BeanUtils.copyProperties(studentDetails, studentDTO);

		Document document = new Document();
		document.append(StudentConstants.STUDENT_NAME, studentDTO.getStudentName());
		document.append(StudentConstants.STUDENT_AGE, studentDTO.getAge());
		document.append(StudentConstants.STUDENT_GRADE, studentDTO.getGrade());

		String studentId = studentDTO.getAge() + studentDTO.getGrade() + studentDTO.getStudentName().charAt(0);
		studentDTO.setstudentId(studentId);
		document.append(StudentConstants.STUDENT_ID, studentId);

		MongoClient mongo = new MongoClient(StudentConstants.HOST, StudentConstants.PORT);
		log.info("StudentDaoImpl : createStudent() : " + StudentConstants.HOST + " "
				+ StudentConstants.PORT);
		MongoDatabase db = mongo.getDatabase(StudentConstants.SCHOOL_DATABASE);
		MongoCollection<Document> collection = db.getCollection(StudentConstants.STUDENT_COLLECTION);
		collection.insertOne(document);

		log.info("Document inserted : StudentId :" + studentDTO.getstudentId());

		StudentRest studentResp = new StudentRest();
		BeanUtils.copyProperties(studentDTO, studentResp);

		mongo.close();
		log.info("Mongo Connection Closed  ");

		return studentResp;
	}

	@Override
	public StudentRest updateStudent(String studentId, StudentRequestModel studentDetails) {
		// TODO Auto-generated method stub

		StudentDTO studentDTO = new StudentDTO();
		BeanUtils.copyProperties(studentDetails, studentDTO);
		studentDTO.setstudentId(studentId);

		MongoClient mongo = new MongoClient(StudentConstants.HOST, StudentConstants.PORT);
		log.info("StudentDaoImpl : updateStudent() : " + StudentConstants.HOST + " "
				+ StudentConstants.PORT);
		MongoDatabase db = mongo.getDatabase(StudentConstants.SCHOOL_DATABASE);
		MongoCollection<Document> collection = db.getCollection(StudentConstants.STUDENT_COLLECTION);

		Bson filter = new Document(StudentConstants.STUDENT_ID, studentId);
		Bson newValue = new Document(StudentConstants.STUDENT_ID, studentDTO.getstudentId())
				.append(StudentConstants.STUDENT_NAME, studentDTO.getStudentName())
				.append(StudentConstants.STUDENT_AGE, studentDTO.getAge())
				.append(StudentConstants.STUDENT_GRADE, studentDTO.getGrade());
		Bson updateOperationDocument = new Document(StudentConstants.UPDATE_AGGREGATION, newValue);
		collection.updateOne(filter, updateOperationDocument);

		log.info("StudentDaoImpl : updateStudent() : Documentupdated " + studentDTO.getstudentId());

		StudentRest rest = new StudentRest();
		BeanUtils.copyProperties(studentDTO, rest);

		mongo.close();
		log.info("Mongo Connection Closed  ");
		return rest;
	}

	@Override
	public List<StudentRest> getStudentListByAgeAndGrade(String age, String grade) {
		
		log.info("StudentDaoImpl : getStudentListByAgeAndGrade "+age+ " "+grade);
		MongoClient mongo = new MongoClient(StudentConstants.HOST, StudentConstants.PORT);
		log.info("StudentDaoImpl : searchStudent() :  " + StudentConstants.HOST + " "
				+ StudentConstants.PORT);
		MongoDatabase db = mongo.getDatabase(StudentConstants.SCHOOL_DATABASE);
		MongoCollection<Document> collection = db.getCollection(StudentConstants.STUDENT_COLLECTION);

		Bson filter = new Document(StudentConstants.STUDENT_AGE, age).append(StudentConstants.STUDENT_GRADE, grade);
		FindIterable<Document> documentList = collection.find(filter);

		List<StudentRest> studentList = new ArrayList<StudentRest>();
		for (Document doc : documentList) {
			StudentRest rest = new StudentRest();
			rest.setAge(doc.getString(StudentConstants.STUDENT_AGE));
			rest.setStudentName(doc.getString(StudentConstants.STUDENT_NAME));
			rest.setGrade(doc.getString(StudentConstants.STUDENT_GRADE));
			rest.setstudentId(doc.getString(StudentConstants.STUDENT_ID));
			studentList.add(rest);
		}
		log.info("StudentList size : " + studentList.size());

		mongo.close();
		log.info("Mongo Connection Closed  ");
		return studentList;
	}

}
