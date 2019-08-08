package com.itechart.error.handling.mvc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = NameNotStartsWithIValidator.class)
public @interface NameNotStartsWithI {
    String message() default "Name cannot start with i name: {name}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
