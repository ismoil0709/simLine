package uz.pdp.simline.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.util.annotations.Username;

public class UsernameValidator implements ConstraintValidator<Username,String> {
    @Override
    public void initialize(Username constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^[^\\s@.,#$%^&*() +!~]{5,14}$"))
            return true;
        throw new InvalidArgumentException("Username");
    }
}
