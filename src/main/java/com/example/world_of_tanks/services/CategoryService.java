package com.example.world_of_tanks.services;

import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import com.example.world_of_tanks.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void seedCategories() {


        if (categoryRepository.count() != 0) {
            return;
        }

        CategoryEnum[] allCategories = CategoryEnum.values();

        int counter = 1;

        for (CategoryEnum categoryName : allCategories) {

            Category category = new Category().setName(categoryName).setDescription("I am category number:" + counter);

            counter++;

            this.categoryRepository.save(category);
        }

    }
}

