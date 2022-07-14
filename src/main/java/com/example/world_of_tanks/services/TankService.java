package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.dto.AddTankDTO;
import com.example.world_of_tanks.models.dto.DeleteTankDTO;
import com.example.world_of_tanks.models.dto.EditTankDTO;
import com.example.world_of_tanks.models.dto.TankDTO;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import com.example.world_of_tanks.repositories.CategoryRepository;
import com.example.world_of_tanks.repositories.TankRepository;
import com.example.world_of_tanks.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TankService {

    private final TankRepository tankRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public TankService(TankRepository tankRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.tankRepository = tankRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public boolean addTank(AddTankDTO addTankDTO, UserDetails userDetails) {

        Optional<Tank> tank = this.tankRepository.findByName(addTankDTO.getName());

        if (tank.isPresent()) {
            return false;
        }

        CategoryEnum categoryEnum = addTankDTO.getCategory();

        Category category = this.categoryRepository.findByName(categoryEnum);

        UserEntity user = userRepository.findByUsername(userDetails.getUsername()).
                orElseThrow();

        Tank tankToSave = new Tank().
                setHealth(addTankDTO.getHealth()).
                setPower(addTankDTO.getPower()).setName(addTankDTO.getName())
                .setCreated(addTankDTO.getCreated());
        tankToSave.setCategory(category).setUser(user);

        this.tankRepository.save(tankToSave);

        return true;


    }

    public boolean editTank(EditTankDTO editTankDTO) {

        Optional<Tank> tank = this.tankRepository.findByName(editTankDTO.getName());

        if (tank.isEmpty()) {
            return false;
        }

        Tank tankToEdit = tank.get();

        tankToEdit.setHealth(editTankDTO.getHealth())
                .setPower(editTankDTO.getPower());

        tankRepository.save(tankToEdit);

        return true;

    }

    public boolean deleteTank(DeleteTankDTO deleteTankDTO) {

        Optional<Tank> tankToDelete = this.tankRepository.findByName(deleteTankDTO.getName());

        if (tankToDelete.isEmpty()) {
            return false;
        }

        Tank tankToEdit = tankToDelete.get();

        tankRepository.delete(tankToEdit);

        return true;
    }

    public List<TankDTO> getTanksOwnedBy(String ownerUsername) {

        return this.tankRepository.findByUserUsername(ownerUsername)
                .stream().map(TankDTO::new).
                collect(Collectors.toList());

    }

    public List<TankDTO> getTanksOwnedByNot(String noOwnerUsername) {

        return this.tankRepository.findByUserUsernameNot(noOwnerUsername)
                .stream().map(TankDTO::new).
                collect(Collectors.toList());
    }


    public List<TankDTO> getAllSorted() {

        return this.tankRepository.findByOrderByHealthDesc()
                .stream().map(TankDTO::new).
                collect(Collectors.toList());
    }
}
