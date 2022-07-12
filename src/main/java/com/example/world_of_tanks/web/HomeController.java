package com.example.world_of_tanks.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/users/home")
    public String getHome() {
        return "home";
    }
}
