package com.example.world_of_tanks.models.dto;


import com.example.world_of_tanks.models.Tank;

public class TankDTO {

    private long id;

    private String name;

    private long health;

    private long power;

    public TankDTO(Tank tank) {
        this.id = tank.getId();
        this.name = tank.getName();
        this.power = tank.getPower();
        this.health = tank.getHealth();
    }

    public long getId() {
        return id;
    }


    public long getHealth() {
        return health;
    }


    public long getPower() {
        return power;
    }


    public String getName() {
        return name;
    }


}
