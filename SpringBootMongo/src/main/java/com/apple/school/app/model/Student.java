package com.apple.school.app.model;

import java.io.Serializable;

public class Student implements Serializable {
	
	
	public String studentName;
	public String age;
	public String grade;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
   public Student(String studentName, String age, String grade) {
	   //this._id = _id;
	   this.studentName = studentName;
	   this.age = age;
	   this.grade = grade;
   }
	
	
	//  public String get_id() { return _id.toHexString(); }
	  //public void set_id(ObjectId _id) { this._id = _id; }
	  
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
