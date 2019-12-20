package com.apple.school.app.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.apple.school.app.util.StudentConstants;

@Documented
@Constraint(validatedBy = StudentGradeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StudentGrade {
	
	public String message() default StudentConstants.ENTER_VALID_GRADE;
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
