package com.example.world_of_tanks.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UserExistValidator.class)
public @interface UserExist {

    String message() default "There is no user with this username!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
