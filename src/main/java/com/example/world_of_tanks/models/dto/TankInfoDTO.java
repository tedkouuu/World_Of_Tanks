package com.example.world_of_tanks.models.dto;

public class TankInfoDTO {

    public TankInfoDTO() {
    }

    private long id;

    private String name;

    private long health;

    private long power;

    public long getId() {
        return id;
    }

    public TankInfoDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TankInfoDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getHealth() {
        return health;
    }

    public TankInfoDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public long getPower() {
        return power;
    }

    public TankInfoDTO setPower(long power) {
        this.power = power;
        return this;
    }
}
