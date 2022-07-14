package com.example.world_of_tanks.scheduler;

import com.example.world_of_tanks.services.TankService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GivingHealthScheduler {

    private final TankService tankService;

    public GivingHealthScheduler(TankService tankService) {
        this.tankService = tankService;
    }

    // След тежките битки, всеки един танк бива поправен и му се възвръща 100 кръв на всеки 12 часа! :)
    @Scheduled(cron = "0 0 10 * * *")
    public void giveTanksHp() {

        this.tankService.giveHpToAllTanks();

    }
}
