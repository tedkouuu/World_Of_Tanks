package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.dto.TankAttackDTO;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import com.example.world_of_tanks.repositories.TankRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BattleService {

    private static final int LIGHT_TANK_EXTRA_DMG = 10;
    private static final int MEDIUM_TANK_EXTRA_DMG = 15;
    private static final int HEAVY_TANK_EXTRA_DMG = 20;

    private final TankRepository tankRepository;

    public BattleService(TankRepository tankRepository) {
        this.tankRepository = tankRepository;
    }

    public boolean attack(TankAttackDTO attackData) {

        Optional<Tank> attackerOpt = this.tankRepository.findById(attackData.getAttackerId());

        Optional<Tank> defenderOpt = this.tankRepository.findById(attackData.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {

            return false;


        } else {

            Tank attacker = attackerOpt.get();

            Tank defender = defenderOpt.get();

            long defenderHealth = defender.getHealth() - attacker.getPower();

            if (attacker.getCategory().getName() == CategoryEnum.LIGHT_TANK) {

                defenderHealth -= LIGHT_TANK_EXTRA_DMG;

            } else if (attacker.getCategory().getName() == CategoryEnum.MEDIUM_TANK) {

                defenderHealth -= MEDIUM_TANK_EXTRA_DMG;

            } else {

                defenderHealth -= HEAVY_TANK_EXTRA_DMG;

            }


            if (defenderHealth <= 0) {
                this.tankRepository.delete(defender);

            } else {
                defender.setHealth(defenderHealth);
                tankRepository.save(defender);
            }

            return true;

        }
    }
}















