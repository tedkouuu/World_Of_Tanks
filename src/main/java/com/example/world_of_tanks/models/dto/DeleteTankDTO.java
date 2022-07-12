package com.example.world_of_tanks.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DeleteTankDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    public String getName() {
        return name;
    }

    public DeleteTankDTO setName(String name) {
        this.name = name;
        return this;
    }
}
