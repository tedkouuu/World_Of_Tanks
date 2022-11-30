package com.example.world_of_tanks.web;

import com.example.world_of_tanks.exceptions.ObjectNotFoundException;
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
            description = "If the tank was not found"
    )
    @GetMapping("/tank/getCategoryId/{id}")
    public ResponseEntity<TankDTO> getGame(@PathVariable Long id) {

        TankDTO tankById = tankService.getTankById(id);

        if (tankById == null) {
            throw new ObjectNotFoundException("Tank was not found!");
        }

        return ResponseEntity.ok(tankById);
    }
}




