package uz.pdp.simline.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.util.annotations.Password;

public class PasswordValidator implements ConstraintValidator<Password,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null)
            return true;
        if (s.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_$%^&+=!])(?=\\S+$).{8,}$"))
            return true;
        throw new InvalidArgumentException("Password");
    }
}
