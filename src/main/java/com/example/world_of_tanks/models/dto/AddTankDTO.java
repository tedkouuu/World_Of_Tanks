package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.enums.CategoryEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddTankDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Positive
    @NotNull
    private long power;

    @Positive
    @NotNull
    private long health;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @NotNull
    private CategoryEnum category;

    public AddTankDTO() {
    }

    public String getName() {
        return name;
    }

    public AddTankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getPower() {
        return power;
    }

    public AddTankDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public AddTankDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public AddTankDTO setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddTankDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}

