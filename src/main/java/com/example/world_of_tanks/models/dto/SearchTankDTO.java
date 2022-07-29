package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.enums.CategoryEnum;

import javax.validation.constraints.NotNull;

public class SearchTankDTO {

    private String name;

    private Integer health;

    private Integer power;

    private CategoryEnum categoryName;

    public String getName() {
        return name;
    }

    public SearchTankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategoryName() {
        return categoryName;
    }

    public SearchTankDTO setCategoryName(CategoryEnum categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public Integer getHealth() {
        return health;
    }

    public SearchTankDTO setHealth(Integer health) {
        this.health = health;
        return this;
    }

    public SearchTankDTO setPower(Integer power) {
        this.power = power;
        return this;
    }

    public Integer getPower() {
        return power;
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                health == null &&
                power == null;
    }
}
