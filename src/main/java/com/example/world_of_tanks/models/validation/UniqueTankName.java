package com.example.world_of_tanks.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueTankNameValidator.class)
public @interface UniqueTankName {

    String message() default "Tank with this name already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
