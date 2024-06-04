package com.oceantech.oceantech.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GeneroValidator implements ConstraintValidator<Genero, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.equals("Masculine") || value.equals("Feminine");
	}

    
}