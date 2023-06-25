package com.example.board.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final String regex = "(?=.*[a-z])(?=.*[0-9])[a-z0-9]{6,20}";

    private final Pattern pattern = Pattern.compile(regex);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
