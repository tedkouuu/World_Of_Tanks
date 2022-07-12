package com.example.world_of_tanks.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditUserDTO {

    @Size(min = 3, max = 10)
    @NotBlank
    private String oldUsername;

    @Size(min = 3, max = 10)
    @NotBlank
    private String newUsername;

    @Size(min = 5, max = 20)
    @NotBlank
    private String fullName;

    @Size(min = 3)
    @NotBlank
    private String password;

    public String getFullName() {
        return fullName;
    }

    public EditUserDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public EditUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public EditUserDTO setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
        return this;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public EditUserDTO setNewUsername(String newUsername) {
        this.newUsername = newUsername;
        return this;
    }
}
