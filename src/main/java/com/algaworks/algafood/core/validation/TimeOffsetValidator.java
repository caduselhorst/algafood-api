package com.algaworks.algafood.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TimeOffsetValidator implements ConstraintValidator<TimeOffset, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String pattern = "^[+-](0[0-9]|1[0-2]):00$";
		
		return value.matches(pattern);
	}

}
