package com.example.world_of_tanks.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DeleteUserDTO {


    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    public String getUsername() {
        return username;
    }

    public DeleteUserDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
