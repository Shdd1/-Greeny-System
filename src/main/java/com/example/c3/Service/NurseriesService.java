package com.example.c3.Service;


import com.example.c3.Api.ApiException;
import com.example.c3.Model.Farmers;
import com.example.c3.Model.Nurseries;
import com.example.c3.Model.Plants;
import com.example.c3.Repository.FarmersRepository;
import com.example.c3.Repository.NurseriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NurseriesService {
    private final NurseriesRepository nurseriesRepository;
    private final FarmersRepository farmersRepository;

    public List<Nurseries> getAllNurseries() {
        return nurseriesRepository.findAll();
    }
    public void addNurseries(Nurseries nurseries, Integer farmer_id){
        Farmers farmer = farmersRepository.findFarmersById(farmer_id);
        if(farmer==null){
            throw new ApiException("Cannot Assign Nurseries to Farmer!");
        }
        nurseries.setRegistration_date(LocalDate.now());
        nurseries.setFarmers(farmer);
        nurseriesRepository.save(nurseries);
    }
    public void updateNurseries(Nurseries nurseries,Integer id){
        Nurseries nurseries1 = nurseriesRepository.findNurseriesById(id);
        if (nurseries1 == null){
            throw new ApiException("Nurseries not found");
        }
        nurseries1.setName(nurseries.getName());
        nurseries1.setAddress(nurseries.getAddress());
        nurseries1.setPhone_number(nurseries.getPhone_number());
        nurseries1.setTypeOfPlants(nurseries.getTypeOfPlants());
        nurseries1.setRegistration_date(nurseries.getRegistration_date());

        nurseriesRepository.save(nurseries1);
    }
    public void deleteNurseries(Integer id){
        Nurseries nurseries = nurseriesRepository.findNurseriesById(id);
        if(nurseries == null){
            throw new ApiException("Nurseries not found");
        }
        nurseriesRepository.delete(nurseries);
    }

    //Assign list of plants to one Nurserie
   public Set<Plants> getListOfPlantsToNurseries(Integer nurseries_id){
        Nurseries nurseries = nurseriesRepository.findNurseriesById(nurseries_id);
        if(nurseries == null){
            throw new ApiException("Cannot Assign Plants to Nurseries!");
        }
        if(nurseries.getPlants().isEmpty()){
            throw new ApiException("Plants not found, There are no plants assigned!");
        }
        return nurseries.getPlants();
   }

    public Set<Nurseries> getNurseriesWithAvailablePlantStock(Integer plant_id) {
        Set <Nurseries> nurseries = nurseriesRepository.findNurseriesWithAvailableStockForPlant(plant_id);
        if (nurseries.isEmpty()) {
            throw new ApiException("No nurseries found with available stock for the specified plant.");
        }
        return nurseries;
    }
}
