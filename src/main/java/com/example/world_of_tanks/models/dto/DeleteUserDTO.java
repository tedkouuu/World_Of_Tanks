package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.validation.UserExist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DeleteUserDTO {


    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 symbols!")
    @NotBlank(message = "Username is required!")
    @UserExist(message = "There is no user with this username")
    private String username;

    public String getUsername() {
        return username;
    }

    public DeleteUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
