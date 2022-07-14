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
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TankService {

    private static final int EXTRA_HP_EVERY_DAY = 100;
    private final TankRepository tankRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public TankService(TankRepository tankRepository, CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.tankRepository = tankRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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

        Tank tankModelMapper = modelMapper.map(addTankDTO, Tank.class);

        tankModelMapper.setUser(user);

        tankModelMapper.setCategory(category);


        // Ако model mapper не работи, мога винаги да си ползвам сетъри!

//        Tank tankToSave = new Tank().
//                setHealth(addTankDTO.getHealth()).
//                setPower(addTankDTO.getPower()).setName(addTankDTO.getName())
//                .setCreated(addTankDTO.getCreated());
//        tankToSave.setCategory(category).setUser(user);

        this.tankRepository.save(tankModelMapper);

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

    // TODO: I CAN USE MODEL MAPPER HERE
    public List<TankDTO> getTanksOwnedBy(String ownerUsername) {

        return this.tankRepository.findByUserUsername(ownerUsername)
                .stream().map(TankDTO::new).
                collect(Collectors.toList());

    }

    // TODO: I CAN USE MODEL MAPPER HERE
    public List<TankDTO> getTanksOwnedByNot(String noOwnerUsername) {

        return this.tankRepository.findByUserUsernameNot(noOwnerUsername)
                .stream().map(TankDTO::new).
                collect(Collectors.toList());
    }


    // TODO: I CAN USE MODEL MAPPER HERE
    public List<TankDTO> getAllSorted() {

        return this.tankRepository.findByOrderByHealthDesc()
                .stream().map(TankDTO::new).
                collect(Collectors.toList());
    }

    public void giveHpToAllTanks() {

        List<Tank> allTanks = this.tankRepository.findAll();

        if (allTanks.isEmpty()) {
            return;
        }

        for (Tank tank : allTanks) {

            tank.setHealth(tank.getHealth() + EXTRA_HP_EVERY_DAY);

            this.tankRepository.save(tank);

        }
    }
}
