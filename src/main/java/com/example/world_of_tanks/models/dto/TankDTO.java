package com.example.world_of_tanks.models.dto;


import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.UserEntity;

public class TankDTO {

    public TankDTO() {
    }

    private long id;

    private String name;

    private long health;

    private long power;

    public UserEntity getUser() {
        return user;
    }

    public TankDTO setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    private UserEntity user;

    public Category getCategory() {
        return category;
    }

    public TankDTO setCategory(Category category) {
        this.category = category;
        return this;
    }

    private Category category;

    public TankDTO(Tank tank) {
        this.id = tank.getId();
        this.name = tank.getName();
        this.power = tank.getPower();
        this.health = tank.getHealth();
        this.user = tank.getUser();
        this.category = tank.getCategory();
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

    public TankDTO setId(long id) {
        this.id = id;
        return this;
    }

    public TankDTO setName(String name) {
        this.name = name;
        return this;
    }

    public TankDTO setHealth(long health) {
        this.health = health;
        return this;
    }

    public TankDTO setPower(long power) {
        this.power = power;
        return this;
    }
}
