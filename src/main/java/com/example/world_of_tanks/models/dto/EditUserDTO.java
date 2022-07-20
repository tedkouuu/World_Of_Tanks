package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.validation.UniqueUserName;
import com.example.world_of_tanks.models.validation.UserExist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditUserDTO {

    @Size(min = 3, max = 10, message = "Old username must be between 3 and 10 characters!")
    @NotBlank(message = "You need the old user username!")
    @UserExist
    private String oldUsername;

    @Size(min = 3, max = 10, message = "New username must be between 3 and 10 characters!")
    @NotBlank(message = "You need the old user username!")
    @UniqueUserName
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
