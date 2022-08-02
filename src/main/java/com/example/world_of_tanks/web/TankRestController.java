package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.TankDTO;
import com.example.world_of_tanks.services.TankService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    // ДОПЪЛНИТЕЛНО ИНФО

    @Tag(name = "Get tank by id", description = "Returns the tank details by its id")
    @Parameter(
            name = "id",
            description = "The ID of the tank",
            required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "If the tank was retrieved successfully"
    )
    @ApiResponse(
            responseCode = "404",
            description = "If the book was not found"
    )
    @GetMapping("/tank/getCategoryId/{id}")
    public ResponseEntity<TankDTO> getGame(@PathVariable Long id) { // ТРЯБВА МИ НЯКАКВО DTO ,КОЕТО ДА ГО ВЪРНА

        return ResponseEntity.ok(tankService.getTankById(id));
    }
}




