package com.example.world_of_tanks.models.dto;

import javax.validation.constraints.Positive;

public class TankAttackDTO {

    @Positive
    private long attackerId;

    @Positive
    private long defenderId;

    public long getAttackerId() {
        return attackerId;
    }

    public TankAttackDTO setAttackerId(long attackerId) {
        this.attackerId = attackerId;
        return this;
    }

    public long getDefenderId() {
        return defenderId;
    }

    public TankAttackDTO setDefenderId(long defenderId) {
        this.defenderId = defenderId;
        return this;
    }
}
