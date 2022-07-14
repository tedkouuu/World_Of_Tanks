package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.dto.TankAttackDTO;
import com.example.world_of_tanks.repositories.TankRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BattleService {

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

            // TODO MAKE LOGIC SMARTED THIS IS DUMB AF

            Tank attacker = attackerOpt.get();

            Tank defender = defenderOpt.get();

            long defenderHealth = defender.getHealth() - attacker.getPower();

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















