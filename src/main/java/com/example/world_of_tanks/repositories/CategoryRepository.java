package com.example.world_of_tanks.repositories;

import com.example.world_of_tanks.models.Category;
import com.example.world_of_tanks.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(CategoryEnum categoryEnum);
}
