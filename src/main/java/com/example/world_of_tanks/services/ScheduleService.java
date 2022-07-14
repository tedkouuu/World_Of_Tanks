package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Tank;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private static final int EXTRA_HP_EVERY_DAY = 100;
    private final TankService tankService;

    public ScheduleService(TankService tankService) {
        this.tankService = tankService;
    }

    public void giveHpToAllTanks() {

        List<Tank> allTanks = this.tankService.findAll();

        if (allTanks.isEmpty()) {
            return;
        }

        for (Tank tank : allTanks) {

            tank.setHealth(tank.getHealth() + EXTRA_HP_EVERY_DAY);

            this.tankService.save(tank);

        }
    }
}
