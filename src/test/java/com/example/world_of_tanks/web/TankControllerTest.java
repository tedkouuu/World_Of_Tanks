package com.example.world_of_tanks.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class TankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test // GET MAPPING EDIT TANK / WITH ADMIN ROLE
    @WithMockUser(roles = {"ADMIN"})
    void testEditTankPageShown() throws Exception {
        mockMvc.perform(get("/tank/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-edit"));
    }

    @Test // GET MAPPING EDIT TANK / WITH ADMIN ROLE
    @WithMockUser(roles = {"USER"})
    void testEditTankWithUserRolePageShown() throws Exception {
        mockMvc.perform(get("/user/role/tank/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-tank-edit"));
    }

    @Test // GET MAPPING DELETE TANK / WITH ADMIN ROLE
    @WithMockUser(roles = {"ADMIN"})
    void testDeleteTankWithAdminRolePageShown() throws Exception {
        mockMvc.perform(get("/tank/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-delete"));
    }

    @Test // GET MAPPING EDIT TANK / WITH ADMIN ROLE
    @WithMockUser(roles = {"USER"})
    void testDeleteTankWithUserRolePageShown() throws Exception {
        mockMvc.perform(get("/user/tank/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-role-tank-delete"));
    }

    @Test // GET MAPPING DELETE TANK / WITH USER ROLE
    @WithMockUser(roles = {"USER"})
    void testDeleteAllTanksWithNoMatterTheRolePageShown() throws Exception {
        mockMvc.perform(get("/tanks/delete/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("delete-all-tanks"));
    }

    @Test // GET MAPPING ADD TANK / WITH NO MATTER ROLE
    @WithMockUser(roles = {"USER"})
    void testAddTankWithNoMatterTheRolePageShown() throws Exception {
        mockMvc.perform(get("/tank/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-add"));
    }
}