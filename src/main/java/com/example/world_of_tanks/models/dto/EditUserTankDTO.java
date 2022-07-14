package com.example.world_of_tanks.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditUserTankDTO {


    @NotBlank
    @Size(min = 2, max = 10)
    private String oldName;

    @NotBlank
    @Size(min = 2, max = 10)
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
