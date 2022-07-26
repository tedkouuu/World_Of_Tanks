package com.example.world_of_tanks.web;

import com.example.world_of_tanks.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
                param("username", "Magda1"). // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ USERNAME, НЯМА IN-MEMORY DB
                        param("fullName", "Magdalena1"). // ТОВА ГО СЛАГАМ НА МЯСТОТО НА userName
                        param("email", "magda@021").  // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ EMAIL
                        param("password", "123").
                param("confirmPassword", "123").
                param("role", "ADMIN").with(csrf()).
                cookie(new Cookie("lang", Locale.FRENCH.getLanguage()))

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/login"));

        verify(mockEmailService).sendRegistrationEmail("magda@02", "Magdalena1",
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
}

























