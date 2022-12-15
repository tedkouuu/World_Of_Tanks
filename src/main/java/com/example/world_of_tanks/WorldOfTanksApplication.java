package com.example.world_of_tanks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class WorldOfTanksApplication {
//
    public static void main(String[] args) {
        SpringApplication.run(WorldOfTanksApplication.class, args);
    }

}
