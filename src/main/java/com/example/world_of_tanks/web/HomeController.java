package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.TankAttackDTO;
import com.example.world_of_tanks.models.dto.TankDTO;
import com.example.world_of_tanks.services.TankService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    private final TankService tankService;


    public HomeController(TankService tankService) {
        this.tankService = tankService;
    }

    @ModelAttribute("tankAttackDTO")
    public TankAttackDTO initBattle() {
        return new TankAttackDTO();
    }


    @GetMapping("/users/home")
    public String getHome(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        // Зареждам корабите на текущо-логнатият потребител

        List<TankDTO> ownTanks = this.tankService.getTanksOwnedBy(userDetails.getUsername());

        //Зареждам корабите на всички останали потребители

        List<TankDTO> enemyTanks = this.tankService.getTanksOwnedByNot(userDetails.getUsername());

        model.addAttribute("ownTanks", ownTanks);
        model.addAttribute("enemyTanks", enemyTanks);

        List<TankDTO> sortedTanks = this.tankService.getAllSorted();

        model.addAttribute("sortedTanks", sortedTanks);

        return "home";
    }
}



















