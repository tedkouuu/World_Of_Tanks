package com.example.world_of_tanks.configuration;

import com.example.world_of_tanks.models.enums.UserRoleEnum;
import com.example.world_of_tanks.repositories.UserRepository;
import com.example.world_of_tanks.configService.WorldOfTanksDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    //Here we have to expose 3 things:
    // 1. PasswordEncoder
    // 2. SecurityFilterChain
    // 3. UserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.
                // define which requests are allowed and which not
                        authorizeRequests().
                // everyone can download static resources (css, js, images)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can login and register
                        antMatchers("/", "/users/login", "/users/register").permitAll().
                antMatchers("/tanks/battle").authenticated().
                // pages available only for admins
                        antMatchers("/pages/admins", "/tanks/add", "/tank/edit", "/tank/delete", "/users/edit", "/users/delete").hasRole(UserRoleEnum.ADMIN.name()).
                // all other pages are available for logger in users

                        anyRequest().
                authenticated().
                and().
                // configuration of form login
                        formLogin().
                // the custom login form
                        loginPage("/users/login").permitAll().
                // the name of the username form field
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the password form field
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where to go in case that the login is successful
                        defaultSuccessUrl("/users/home").
                // where to go in case that the login failed
                        failureForwardUrl("/users/login-error").
                and().
                // configure logout
                        logout().
                // which is the logout url
                        logoutUrl("/users/logout").
                // invalidate the session and delete the cookies
                        invalidateHttpSession(true).
                deleteCookies("JSESSIONID");


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new WorldOfTanksDetailsService(userRepository);
    }


}
