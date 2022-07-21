package com.example.world_of_tanks.models.dto;

import com.example.world_of_tanks.models.validation.TankExist;
import com.example.world_of_tanks.models.validation.UniqueTankName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditUserTankDTO {


    @NotBlank(message = "Old name is required!")
    @Size(min = 2, max = 10, message = "Old name must be between 2 and 10 characters!")
    @TankExist
    private String oldName;

    @NotBlank(message = "New name is required!")
    @Size(min = 2, max = 10, message = "New name must be between 2 and 10 characters!")
    @UniqueTankName
    private String newName;

    public String getOldName() {
        return oldName;
    }

    public EditUserTankDTO setOldName(String oldName) {
        this.oldName = oldName;
        return this;
    }

    public String getNewName() {
        return newName;
    }

    public EditUserTankDTO setNewName(String newName) {
        this.newName = newName;
        return this;
    }
}
