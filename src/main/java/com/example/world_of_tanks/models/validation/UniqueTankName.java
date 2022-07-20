package com.example.world_of_tanks.models.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Обяснява че тази анотация трябва да бъде активна RUNTIME
@Target(ElementType.FIELD) // Казвам че искам да анотирам полета с нея
@Constraint(validatedBy = UniqueTankNameValidator.class) // ТУК ТРЯБВА ДА СИ НАПРАВЯ ВАЛИДАТОР, КОЙТО ДА ДЪРЖИ ЛОГИКАТА
public @interface UniqueTankName {

    String message() default "Tank with this name already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
