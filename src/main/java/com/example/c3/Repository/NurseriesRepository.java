package com.example.c3.Repository;

import com.example.c3.Model.Nurseries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NurseriesRepository extends JpaRepository<Nurseries, Integer> {
    Nurseries findNurseriesById(Integer id);
    @Query("SELECT n FROM Nurseries n JOIN n.plants p WHERE p.id = :plantId AND p.stock > 0")
    Set<Nurseries> findNurseriesWithAvailableStockForPlant(Integer plantId);
    @Query("SELECT n FROM Nurseries n WHERE n.address LIKE %:location%")
    List<Nurseries> findNurseriesByLocation(@Param("location") String location);

}
