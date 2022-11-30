package com.example.world_of_tanks.web;

import com.example.world_of_tanks.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.param;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService mockEmailService;

    @Test
        // GET MAPPING REGISTER
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                param("username", "test8user").
                param("fullName", "fullnametest").
                param("email", "email@test").
                param("password", "123").
                param("confirmPassword", "123").
                param("role", "ADMIN").with(csrf()).
                cookie(new Cookie("lang", Locale.FRENCH.getLanguage()))


        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/login"));

        verify(mockEmailService).sendRegistrationEmail("email@test", "fullnametest",
                Locale.FRENCH);

    }

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                param("username", "tedkou").
                param("password", "123").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }


    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testEditPageShown() throws Exception {
        mockMvc.perform(get("/users/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-edit"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testUsersDeletePageShown() throws Exception {
        mockMvc.perform(get("/users/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("users-delete"));
    }

    @Test
    void testTankInfoPageShown() throws Exception {
        mockMvc.perform(get("/tanks/info"))
                .andExpect(status().isOk())
                .andExpect(view().name("tanks-info"));
    }

    @Test
    void testUserDeleteWithAdminRole() throws Exception {
        mockMvc.perform(post("/users/delete").
                param("username", "exaaaam").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testUserEditWithAdminRole() throws Exception {
        mockMvc.perform(post("/users/edit").
                param("oldUsername", "krisi").
                param("newUsername", "examtest9").
                param("fullName", "noname").
                param("password", "123").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }
}

























