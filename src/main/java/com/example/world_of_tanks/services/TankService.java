package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.UserEntity;
import com.example.world_of_tanks.models.dto.*;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import com.example.world_of_tanks.repositories.CategoryRepository;
import com.example.world_of_tanks.repositories.TankRepository;
import com.example.world_of_tanks.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TankService {

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

        UserEntity user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();

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

        tankToEdit.setHealth(editTankDTO.getHealth()).setPower(editTankDTO.getPower());

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

        List<TankDTO> ownedTanks = new ArrayList<>();

        List<Tank> tanks = this.tankRepository.findByUserUsername(ownerUsername);

        for (Tank tank : tanks) {

            TankDTO tankDto = modelMapper.map(tank, TankDTO.class);

            ownedTanks.add(tankDto);
        }

        return ownedTanks;

    }


    public List<TankDTO> getTanksOwnedByNot(String noOwnerUsername) {

        List<TankDTO> enemyTanks = new ArrayList<>();

        List<Tank> tanks = this.tankRepository.findByUserUsernameNot(noOwnerUsername);

        for (Tank tank : tanks) {

            TankDTO tankDto = modelMapper.map(tank, TankDTO.class);
            enemyTanks.add(tankDto);
        }

        return enemyTanks;
    }


    public List<TankDTO> getAllSorted() {

        List<TankDTO> sortedTanks = new ArrayList<>();

        List<Tank> tanks = this.tankRepository.findByOrderByHealthDesc();

        for (Tank tank : tanks) {

            TankDTO tankDto = modelMapper.map(tank, TankDTO.class);

            sortedTanks.add(tankDto);
        }

        return sortedTanks;
    }

    public List<Tank> findAll() {

        return this.tankRepository.findAll();
    }

    public void save(Tank tank) {

        this.tankRepository.save(tank);
    }


    public boolean editUserTank(EditUserTankDTO editUserTankDTO, UserDetails userDetails) {

        List<Tank> allUserTanks = this.tankRepository.findByUserUsername(userDetails.getUsername());

        Optional<Tank> tank = this.tankRepository.findByName(editUserTankDTO.getOldName());

        if (allUserTanks.isEmpty() || tank.isEmpty()) {

            return false;
        }

        if (!allUserTanks.contains(tank.get())) {

            return false;
        }

        Tank tankToSet = tank.get();

        tankToSet.setName(editUserTankDTO.getNewName());

        this.tankRepository.save(tankToSet);

        return true;
    }

    public boolean deleteUserTank(DeleteUserTankDTO deleteUserTankDTO, UserDetails userDetails) {

        List<Tank> allUserTanks = this.tankRepository.findByUserUsername(userDetails.getUsername());

        Optional<Tank> tank = this.tankRepository.findByName(deleteUserTankDTO.getName());

        if (allUserTanks.isEmpty() || tank.isEmpty()) {

            return false;
        }

        if (!allUserTanks.contains(tank.get())) {

            return false;
        }


        this.tankRepository.delete(tank.get());

        return true;

    }
//
//    public TankDTO getTankById(Long id) {
//
//        return this.tankRepository.findById(id);
//    }
}











