package com.example.world_of_tanks.web;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerGreenMailTest {

    @Value("${mail.host}")
    private String mailHost;
    @Value("${mail.port}")
    private Integer mailPort;
    @Value("${mail.username}")
    private String userName;
    @Value("${mail.password}")
    private String password;


    private GreenMail greenMail;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        greenMail = new GreenMail(new ServerSetup(mailPort, mailHost, "smtp"));
        greenMail.start();
        greenMail.setUser(userName, password);
    }

    @AfterEach
    void tearDown() {
        greenMail.stop();
    }

    @Test
    void testRegistration() throws Exception { // ---> ТРЯБВА ВИНАГИ СТОЙНОСТИТЕ НА GreenMailTest ДА СА РАЗЛИЧНИ ОТ ТЕЗИ В UserTest

        mockMvc.perform(post("/users/register").
                param("username", "Sadno1"). // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ USERNAME, НЯМА IN-MEMORY DB
                        param("fullName", "Sando1"). // ТОВА ГО СЛАГАМ НА МЯСТОТО НА userName
                        param("email", "Sando1@123").  // ВСЕКИ ПЪТ ТРЯБВА ДА ПРОМЕНЯМ EMAIL
                        param("password", "123").
                param("confirmPassword", "123").
                param("role", "ADMIN").with(csrf()).
                cookie(new Cookie("lang", Locale.FRENCH.getLanguage()))

        ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/users/login"));

        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();

        Assertions.assertEquals(1, receivedMessages.length);

    }
}
