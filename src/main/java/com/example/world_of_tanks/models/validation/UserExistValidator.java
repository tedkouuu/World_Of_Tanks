package com.example.world_of_tanks.models.validation;

import com.example.world_of_tanks.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistValidator implements ConstraintValidator<UserExist, String> {

    private final UserRepository userRepository;

    public UserExistValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userRepository.
                findByUsername(value).
                isPresent();
    }
}
