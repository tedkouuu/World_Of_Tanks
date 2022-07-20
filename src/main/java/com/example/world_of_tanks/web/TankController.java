package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.*;
import com.example.world_of_tanks.services.TankService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TankController {

    private final TankService tankService;

    public TankController(TankService tankService) {
        this.tankService = tankService;
    }

    @ModelAttribute("addTankDTO")
    public AddTankDTO initTankAddModel() {
        return new AddTankDTO();
    }

    @ModelAttribute("editTankDTO")
    public EditTankDTO editTankAddModel() {
        return new EditTankDTO();
    }

    @ModelAttribute("editUserTankDTO")
    public EditUserTankDTO editUserTankAddModel() {
        return new EditUserTankDTO();
    }

    @ModelAttribute("deleteTankDTO")
    public DeleteTankDTO deleteTankAddModel() {
        return new DeleteTankDTO();
    }

    @ModelAttribute("deleteUserTankDTO")
    public DeleteUserTankDTO deleteOwnedTank() {
        return new DeleteUserTankDTO();
    }

    @GetMapping("/tank/add")
    public String getTank() {

        return "tank-add";
    }

    @PostMapping("/tank/add")
    public String addShip(@Valid AddTankDTO addTankDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTankDTO", addTankDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTankDTO", bindingResult);

            return "redirect:/tank/add";
        }

        this.tankService.addTank(addTankDTO, userDetails);

        return "redirect:/users/home";
    }

    @GetMapping("/tank/edit")
    public String getEdit() {
        return "tank-edit";
    }

    @PostMapping("/tank/edit")
    public String edit(@Valid EditTankDTO editTankDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.tankService.editTank(editTankDTO)) {
            redirectAttributes.addFlashAttribute("editTankDTO", editTankDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editTankDTO", bindingResult);

            return "redirect:/tank/edit";
        }

        this.tankService.editTank(editTankDTO);

        return "redirect:/users/home";
    }

    @GetMapping("/user/role/tank/edit")
    public String getUserTankEdit() {
        return "user-tank-edit";
    }

    @PostMapping("/user/role/tank/edit")
    public String edit(@Valid EditUserTankDTO editUserTankDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors() || !this.tankService.editUserTank(editUserTankDTO, userDetails)) {
            redirectAttributes.addFlashAttribute("editUserTankDTO", editUserTankDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editUserTankDTO", bindingResult);

            return "redirect:/user/role/tank/edit";
        }

        this.tankService.editUserTank(editUserTankDTO, userDetails);

        return "redirect:/users/home";

    }

    @GetMapping("/tank/delete")
    public String getDelete() {
        return "tank-delete";
    }

    @PostMapping("/tank/delete")
    public String edit(@Valid DeleteTankDTO deleteTankDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.tankService.deleteTank(deleteTankDTO)) {
            redirectAttributes.addFlashAttribute("deleteTankDTO", deleteTankDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deleteTankDTO", bindingResult);

            return "redirect:/tank/delete";
        }

        this.tankService.deleteTank(deleteTankDTO);

        return "redirect:/users/home";
    }

    @GetMapping("/user/tank/delete")
    public String getUserDelete() {
        return "user-role-tank-delete";
    }

    @PostMapping("/user/tank/delete")
    public String deleteUserTank(@Valid DeleteUserTankDTO deleteUserTankDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors() || !this.tankService.deleteUserTank(deleteUserTankDTO, userDetails)) {
            redirectAttributes.addFlashAttribute("deleteUserTankDTO", deleteUserTankDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.deleteUserTankDTO", bindingResult);

            return "redirect:/user/tank/delete";
        }

        this.tankService.deleteUserTank(deleteUserTankDTO, userDetails);

        return "redirect:/users/home";
    }

    @GetMapping("/tank/getCategoryId")
    @ResponseBody
    public String getCategoryId(int tankId) {
        // this.tankService.find
        return "33";
    }
}
