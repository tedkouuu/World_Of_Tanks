package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.TankDTO;
import com.example.world_of_tanks.services.TankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TankRestController {

    private final TankService tankService;

    public TankRestController(TankService tankService) {
        this.tankService = tankService;
    }

    @GetMapping("/tank/getCategoryId/{id}")
    public ResponseEntity<TankDTO> getGame(@PathVariable Long id) { // ТРЯБВА МИ НЯКАКВО DTO ,КОЕТО ДА ГО ВЪРНА

        return ResponseEntity.ok(tankService.getTankById(id));
    }
}




