package uz.pdp.simline.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.simline.util.validator.SurnameValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SurnameValidator.class)
public @interface Surname {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
