package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.validation.TankExist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class EditTankDTO {

    @NotBlank(message = "Tank name is required!")
    @Size(min = 2, max = 10,message = "Tank name must be between 2 and 10 characters")
    @TankExist(message = "Tank with this name doesn't exist!")
    private String name;

    @Positive
    @NotNull
    private long power;

    @Positive
    @NotNull
    private long health;

    public String getName() {
        return name;
    }

    public EditTankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public EditTankDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public EditTankDTO setHealth(long health) {
        this.health = health;
        return this;
    }
}
