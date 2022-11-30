package com.example.world_of_tanks.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TankExistValidator.class)
public @interface TankExist {

    String message() default "There is no tank with this name!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}