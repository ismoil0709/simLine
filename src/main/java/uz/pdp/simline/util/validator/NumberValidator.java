package uz.pdp.simline.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.simline.exception.InvalidArgumentException;
import uz.pdp.simline.util.annotations.Number;

public class NumberValidator implements ConstraintValidator<Number, java.lang.Number> {
    @Override
    public boolean isValid(java.lang.Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null)
            return true;
        if (number.intValue() >= 0 || number.doubleValue() >= 0)
            return true;
        throw new InvalidArgumentException("Number");
    }
}
