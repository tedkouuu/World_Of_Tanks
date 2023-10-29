package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.TankAttackDTO;
import com.example.world_of_tanks.services.BattleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

//
@Controller
public class BattleController {

    private final BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/tanks/battle")
    public String battle(@Valid TankAttackDTO tankAttackDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.battleService.attack(tankAttackDTO)) {
            redirectAttributes.addFlashAttribute("tankAttackDTO", tankAttackDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.tankAttackDTO", bindingResult);

            return "redirect:/users/home";
        }

        this.battleService.attack(tankAttackDTO);


        return "redirect:/users/home";
    }

}
