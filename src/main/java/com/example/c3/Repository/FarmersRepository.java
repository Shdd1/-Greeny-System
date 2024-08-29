package com.example.c3.Repository;


import com.example.c3.Model.Farmers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmersRepository extends JpaRepository<Farmers, Integer> {
    Farmers findFarmersById(Integer id);
    @Query("SELECT f FROM Farmers f WHERE f.address LIKE %:location%")
    List<Farmers> findFarmersByLocation(@Param("location") String location);
}
