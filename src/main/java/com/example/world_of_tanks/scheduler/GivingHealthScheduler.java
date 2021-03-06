package com.example.world_of_tanks.scheduler;

import com.example.world_of_tanks.services.ScheduleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GivingHealthScheduler {

    private final ScheduleService scheduleService;

    public GivingHealthScheduler(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // След тежките битки, всеки един танк бива поправен и му се възвръща 100 кръв на всеки 12 часа! :)
    @Scheduled(cron = "0 0 12 * * *")
    public void giveTanksHp() {

        this.scheduleService.giveHpToAllTanks();

    }
}
