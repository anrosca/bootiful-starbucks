package com.endava.drinks;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDrinkTypeConstraintValidator.class)
public @interface ValidDrinkType {
    String message() default "{ValidDrinkType.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
