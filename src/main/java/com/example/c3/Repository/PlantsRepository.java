package com.example.c3.Repository;


import com.example.c3.Model.Plants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlantsRepository extends JpaRepository<Plants, Integer> {
    Plants findPlantsById(Integer id);
    Set<Plants> findPlantsByCategory(String category);

}
