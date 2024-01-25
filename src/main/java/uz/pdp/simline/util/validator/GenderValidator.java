package uz.pdp.simline.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.util.annotations.Gender;

public class GenderValidator implements ConstraintValidator<Gender, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^(MALE|FEMALE|UNKNOWN)$"))
            return true;
        throw new InvalidArgumentException("Gender");
    }
}
