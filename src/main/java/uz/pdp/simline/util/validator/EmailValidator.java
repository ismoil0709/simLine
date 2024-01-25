package uz.pdp.simline.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.util.annotations.Email;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"))
            return true;
        throw new InvalidArgumentException("Email");
    }
}
