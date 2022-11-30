package com.example.world_of_tanks.repositories;

import com.example.world_of_tanks.models.Tank;
import com.example.world_of_tanks.models.dto.SearchTankDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TankSpecification implements Specification<Tank> {

    private final SearchTankDTO searchTankDTO;

    public TankSpecification(SearchTankDTO searchTankDTO) {
        this.searchTankDTO = searchTankDTO;
    }

    @Override
    public Predicate toPredicate(Root<Tank> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (searchTankDTO.getName() != null && !searchTankDTO.getName().isEmpty()) {
            p.getExpressions().add(
                    cb.and(cb.equal(root.get("name"), searchTankDTO.getName()))
            );
        }

        if (searchTankDTO.getHealth() != null) {
            p.getExpressions().add(
                    cb.and(cb.greaterThanOrEqualTo(root.get("health"), searchTankDTO.getHealth()))
            );
        }


        if (searchTankDTO.getPower() != null) {
            p.getExpressions().add(
                    cb.and(cb.lessThanOrEqualTo(root.get("power"), searchTankDTO.getPower()))
            );
        }

        return p;
    }
}
