package com.project.lingo.validation;

import com.project.lingo.Presentation.dto.UserDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WachtwoordMatchesValidator implements ConstraintValidator<WachtwoordMatches, Object> {
    @Override
    public void initialize(final WachtwoordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
