package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.enums.UserRoleEnum;
import com.example.world_of_tanks.models.validation.PasswordsMatch;
import com.example.world_of_tanks.models.validation.UniqueUserEmail;
import com.example.world_of_tanks.models.validation.UniqueUserName;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match!"
)
public class RegisterDTO {

    @Size(min = 3, max = 10, message = "Username must be between 3 and 10 symbols")
    @NotBlank(message = "Username should not be empty!")
    @UniqueUserName
    private String username;

    @Size(min = 5, max = 20)
    @NotBlank
    private String fullName;

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email must be provided!")
    @UniqueUserEmail(message = "Email is already taken!")
    private String email;

    @Size(min = 3)
    @NotBlank
    private String password;

    @Size(min = 3)
    @NotBlank
    private String confirmPassword;

    @NotNull
    private UserRoleEnum role;

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public RegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" + "userName='" + getUsername() + '\'' + ", fullName='" + fullName + '\'' + ", email='" + email + '\'' + ", password='" + password + '\'' + ", confirmPassword='" + confirmPassword + '\'' + '}';
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public RegisterDTO setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
