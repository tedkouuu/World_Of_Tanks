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
        // POST MAPPING REGISTER

        // С ТОЗИ ТЕСТ, ТЕСТВАМ ДАЛИ РЕАЛНО СЕ ИЗПЪЛНЯВА .sendRegistrationEmail
        // НЯМАМ IN MEMORY DB, ТАКА ЧЕ ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ СТОЙНОСТИТЕ ЗА USERNAME И PASSWORD
    void testUserRegistration() throws Exception {
        mockMvc.perform(post("/users/register").
                param("username", "Mara321"). // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ USERNAME, НЯМА IN-MEMORY DB
                        param("fullName", "Marata1"). // ТОВА ГО СЛАГАМ НА МЯСТОТО НА userName
                        param("email", "Mara@321").  // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ EMAIL
                        param("password", "123").
                param("confirmPassword", "123").
                param("role", "ADMIN").with(csrf()).
                cookie(new Cookie("lang", Locale.FRENCH.getLanguage()))

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/login"));

        verify(mockEmailService).sendRegistrationEmail("Mara@321", "Marata1",
                Locale.FRENCH);

    }


    @Test
        // GET MAPPING LOGIN
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
        // POST MAPPING LOGIN
    void testUserLogin() throws Exception {
        mockMvc.perform(post("/users/login").
                param("username", "tedkou").
                param("password", "123").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }

    // GET MAPPING EDIT USER / ADMIN ROLE
    @Test
    @WithMockUser(roles = {"ADMIN"})
    // ТРЯБВА ДА РАЗБЕРА КАК ДА ИЗЛЪЖА ИДЕТО ЧЕ ТОЗИ USER ИМА СЕСИЯ
    void testEditPageShown() throws Exception {
        mockMvc.perform(get("/users/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-edit"));
    }

    @Test // GET NAPPING DELETE USERS / ADMIN ROLE
    @WithMockUser(roles = {"ADMIN"})
    void testUsersDeletePageShown() throws Exception {
        mockMvc.perform(get("/users/delete"))
                .andExpect(status().isOk())
                .andExpect(view().name("users-delete"));
    }

    // GET MAPPING TANK INFO
    @Test
    void testTankInfoPageShown() throws Exception {
        mockMvc.perform(get("/tanks/info"))
                .andExpect(status().isOk())
                .andExpect(view().name("tanks-info"));
    }

    @Test // POST MAPPING DELETE USER / ADMIN ROLE
    @WithMockUser(roles = {"ADMIN"})
    void testUserDeleteWithAdminRole() throws Exception {
        mockMvc.perform(post("/users/delete").
                param("username", "tedko499").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }

    @Test // POST MAPPING DELETE USER / ADMIN ROLE
    @WithMockUser(roles = {"ADMIN"})
    void testUserEditWithAdminRole() throws Exception {
        mockMvc.perform(post("/users/edit").
                param("oldUsername", "mara12").
                param("newUsername", "silenkon").
                param("fullName", "leshoqd").
                param("password", "123").
                with(csrf())
        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/home"));
    }
}

























