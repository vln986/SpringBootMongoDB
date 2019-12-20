package com.apple.school.app.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.apple.school.app.util.StudentConstants;

public class StudentGradeValidator implements ConstraintValidator<StudentGrade, String>{
	
	@Override
    public void initialize(StudentGrade studentGrade) {
    }

	@Override
	public boolean isValid(String grade, ConstraintValidatorContext cvc) {
		// TODO Auto-generated method stub
		return grade != null & grade.trim()!= StudentConstants.EMPTY_STRING && grade.length() == 1 
				&& (grade.equalsIgnoreCase(StudentConstants.GRADE_A) || grade.equalsIgnoreCase(StudentConstants.GRADE_B) 
						|| grade.equalsIgnoreCase(StudentConstants.GRADE_C));
	}

}
