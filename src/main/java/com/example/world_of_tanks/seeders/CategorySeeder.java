package com.example.world_of_tanks.seeders;

import com.example.world_of_tanks.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final CategoryService categoryService;

    public CategorySeeder(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.categoryService.seedCategories();
    }
}
