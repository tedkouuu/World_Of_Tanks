package com.example.world_of_tanks.web;

import com.example.world_of_tanks.models.dto.AddTankDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testEditTankPageShown() throws Exception {
        mockMvc.perform(get("/tank/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-edit"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testEditTankWithUserRolePageShown() throws Exception {
        mockMvc.perform(get("/user/role/tank/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-tank-edit"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testDeleteTankWithAdminRolePageShown() throws Exception {
        mockMvc.perform(get("/tank/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-delete"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testDeleteTankWithUserRolePageShown() throws Exception {
        mockMvc.perform(get("/user/tank/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-role-tank-delete"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testDeleteAllTanksWithNoMatterTheRolePageShown() throws Exception {
        mockMvc.perform(get("/tanks/delete/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("delete-all-tanks"));
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void testAddTankWithNoMatterTheRolePageShown() throws Exception {
        mockMvc.perform(get("/tank/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("tank-add"));
    }

    @Test
    void testTankInfoPageShown() throws Exception {
        mockMvc.perform(get("/tanks/info"))
                .andExpect(status().isOk())
                .andExpect(view().name("tanks-info"))
                .andExpect(model().attributeExists("allTanks"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testTankDeleteErrorAdminRole() throws Exception {
        mockMvc.perform(post("/tank/delete").
                        param("name", "tedko6969").
                        with(csrf())
                ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/tank/delete"))
                .andExpect(flash().attributeExists("deleteTankDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.deleteTankDTO"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testAddShip() throws Exception {

        mockMvc.perform(post("/tank/add")
                        .param("name", "toooooooooLongUsername")
                        .param("power", "1")
                        .param("health", "4")
                        .param("date", "2022-08-05")
                        .param("category", "MEDIUM_TANK")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/tank/add"))
                .andExpect(flash().attributeExists("addTankDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addTankDTO"));

    }
}