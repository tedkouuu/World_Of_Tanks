package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.*;
import com.example.world_of_tanks.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    @ModelAttribute("registerDTO")
    public RegisterDTO initUserRegisterModel() {
        return new RegisterDTO();
    }

    @ModelAttribute("editUserDTO")
    public EditUserDTO editUserDTO() {
        return new EditUserDTO();
    }

    @ModelAttribute("deleteUserDTO")
    public DeleteUserDTO deleteUserDTO() {
        return new DeleteUserDTO();
    }


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {

        return "register";
    }

    @PostMapping("/users/register")
    public String register(@Valid RegisterDTO registerDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors() || !this.userService.register(registerDTO)) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO",
                    bindingResult);

            return "redirect:/users/register";
        }

        this.userService.register(registerDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/edit")
    public String getUsersEdit() {
        return "user-edit";
    }

    @PostMapping("/users/edit")
    public String edit(@Valid EditUserDTO editUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.editUser(editUserDTO)) {
            redirectAttributes.addFlashAttribute("editUserDTO", editUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserDTO", bindingResult);

            return "redirect:/users/edit";
        }

        this.userService.editUser(editUserDTO);

        return "redirect:/";
    }

    @GetMapping("/users/delete")
    public String getDelete() {
        return "users-delete";
    }

    @PostMapping("/users/delete")
    public String edit(@Valid DeleteUserDTO deleteUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.deleteUser(deleteUserDTO)) {
            redirectAttributes.addFlashAttribute("deleteUserDTO", deleteUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deleteUserDTO", bindingResult);

            return "redirect:/users/delete";
        }

        this.userService.deleteUser(deleteUserDTO);

        return "redirect:/";
    }
}
