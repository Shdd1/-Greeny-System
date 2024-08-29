package com.example.c3.Repository;

import com.example.c3.Model.GardeningTools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardeningToolsRepository extends JpaRepository<GardeningTools,Integer> {
    GardeningTools findGardeningToolsById(Integer toolsId);
}
