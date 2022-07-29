package com.example.world_of_tanks.models.dto;

public class SearchTankDTO {

    private String name;

    private long health;

    private long power;

    public String getName() {
        return name;
    }

    public SearchTankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public SearchTankDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public SearchTankDTO setPower(long power) {
        this.power = power;
        return this;
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                health == 0 &&
                power == 0;
    }
}
