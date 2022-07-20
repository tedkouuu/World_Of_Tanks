package com.example.world_of_tanks.models.validation;

import com.example.world_of_tanks.repositories.TankRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTankNameValidator implements ConstraintValidator<UniqueTankName, String> {

    private final TankRepository tankRepository;

    public UniqueTankNameValidator(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return this.tankRepository.findByName(value).isEmpty();
    }
}
