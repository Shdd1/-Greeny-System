package com.example.c3.Service;

import com.example.c3.Api.ApiException;
import com.example.c3.Model.Farmers;
import com.example.c3.Model.Nurseries;
import com.example.c3.Model.Plants;
import com.example.c3.Repository.FarmersRepository;
import com.example.c3.Repository.NurseriesRepository;
import com.example.c3.Repository.OrdersRepository;
import com.example.c3.Repository.PlantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlantsService {
    private final PlantsRepository plantsRepository;
    private final FarmersRepository farmersRepository;
    private final NurseriesRepository nurseriesRepository;


    public List<Plants> getAllPlants() {
        return plantsRepository.findAll();
    }

    public void addPlant(Plants plants,Integer farmer_id , Integer nurseries_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        Nurseries nurseries = nurseriesRepository.findNurseriesById(nurseries_id);

        if (farmers == null || nurseries == null) {
            throw new ApiException("Cannot Assign Plants to Nurseries");
        }
        plants.setFarmers(farmers);
        plants.setNurseries(nurseries);
        plantsRepository.save(plants);
    }

    public void updatePlant(Plants plants, Integer id) {
        Plants plant = plantsRepository.findPlantsById(id);
        if (plant == null) {
            throw new ApiException("Plant not found");
        }
        plant.setName(plants.getName());
        plant.setDescription(plants.getDescription());
        plant.setPlantingSeason(plants.getPlantingSeason());
        plant.setLightRequirements(plants.getLightRequirements());
        plant.setCategory(plants.getCategory());
        plant.setPrice(plants.getPrice());
        plant.setSoilType(plants.getSoilType()); // update
        plant.setSoilTypeRequirements(plants.getSoilTypeRequirements());
        plant.setTemperatureRequirements(plants.getTemperatureRequirements());
        plant.setStock(plants.getStock());
        plant.setWateringNeeds(plants.getWateringNeeds()); //update
        plant.setWaterRequirements(plants.getWaterRequirements());
        plant.setGrowthTime(plants.getGrowthTime());

        plantsRepository.save(plant);
    }

    public void deletePlants(Integer id) {
        Plants plants = plantsRepository.findPlantsById(id);
        if (plants == null) {
            throw new ApiException("Plants not found");
        }
        plantsRepository.delete(plants);
    }

    public Plants lightRequirements (Integer farmer_id , Integer plants_id){
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants not found");
        }
        if (p.getLightIntensity().equalsIgnoreCase("sun")) {
            p.setLightRequirements("Full Sun Plants:" +
                    "- Characteristics: Require 6 or more hours of direct sunlight daily." +
                    "- Placement: Full sun areas in the garden or south-facing windows indoors.");
        }else if (p.getLightIntensity().equalsIgnoreCase("partial")) {
            p.setLightRequirements("Partial Sun/Shade Plants:" +
                    "Characteristics: Thrive with 3-6 hours of direct sunlight or filtered light." +
                    "Placement: Areas with dappled sunlight or light shade.");

        }else if (p.getLightIntensity().equalsIgnoreCase("shade")) {
            p.setLightRequirements("Full Shade Plants:"+
                    "Characteristics: Require less than 3 hours of direct sunlight or thrive in indirect light." +
                    "Placement: Areas with full shade or north-facing windows.");
        }else {
            throw new ApiException("Invalid Light Intensity!");
        }
        plantsRepository.save(p);
        return p;
    }

    public Plants waterRequirements(Integer farmer_id , Integer plants_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants not found");
        }

        if (p.getWateringNeeds().equalsIgnoreCase("low")) {
            p.setWaterRequirements("- Characteristics: Tolerant of dry conditions; often have deep root systems.\n" +
                    "- Watering: Allow soil to dry out between waterings; infrequent watering.");

        }else if (p.getWateringNeeds().equalsIgnoreCase("moderate")) {
            p.setWaterRequirements("- Characteristics: Prefer evenly moist soil but can tolerate slight drying.\n" +
                    "- Watering: Water when the top inch of soil is dry; maintain consistent moisture.");

        }else if (p.getWateringNeeds().equalsIgnoreCase("high")) {
            p.setWaterRequirements("- Characteristics: Require consistently moist or wet soil; often grow in wetlands.\n" +
                    "- Watering: Keep soil consistently moist; may need frequent watering.");
        }else {
            throw new ApiException("Invalid Watering Needs!");
        }
        plantsRepository.save(p);
        return p;
    }

    public Plants temperatureRequirements (Integer farmer_id , Integer plants_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants not found");
        }
        if (p.getSeasonPlants().equalsIgnoreCase("cool")) {
            p.setTemperatureRequirements("Cool-Season Plants:"+
                    "- Characteristics: Thrive in cooler temperatures, often tolerate light frosts." +
                    "- Optimal Temperature: 40-75°F (4-24°C).");
        }else if (p.getSeasonPlants().equalsIgnoreCase("warm")) {
            p.setTemperatureRequirements("Warm-Season Plants:" +
                    "- Characteristics: Require warmer temperatures to grow and produce." +
                    "- Optimal Temperature: 60-85°F (15-29°C).");
        }else if (p.getSeasonPlants().equalsIgnoreCase("tropical")) {
            p.setTemperatureRequirements("Tropical Plants:" +
                    "- Characteristics: Need consistently warm temperatures, sensitive to cold." +
                    "- Optimal Temperature: 65-95°F (18-35°C).");
        }else if (p.getSeasonPlants().equalsIgnoreCase("subtropical")) {
            p.setTemperatureRequirements("Subtropical Plants:" +
                    "- Characteristics: Can handle cooler temperatures but prefer warm climates." +
                    "- Optimal Temperature: 50-85°F (10-29°C).");
        }else {
            throw new ApiException("Invalid Season Plants!");
        }
        plantsRepository.save(p);
        return p;
    }


    public Plants soilTypeRequirements (Integer farmer_id , Integer plants_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants not found");
        }

        if(p.getSoilType().equalsIgnoreCase("sandy")){
            p.setSoilTypeRequirements("- Characteristics: Coarse texture, drains quickly, low in nutrients, warms up fast." +
                    "- Best for: Plants that require well-drained soil and can tolerate lower nutrient levels." );

        } else if (p.getSoilType().equalsIgnoreCase("clay")) {
            p.setSoilTypeRequirements("- Characteristics: Fine texture, retains water well, tends to be heavy and slow to drain, high in nutrients.\n" +
                    "- Best for: Plants that can handle higher moisture levels and benefit from rich soil.");

        }  else if (p.getSoilType().equalsIgnoreCase("silt")) {
            p.setSoilTypeRequirements("- Characteristics: Smooth texture, retains moisture better than sand, holds nutrients well.\n" +
                    "- Best for: A wide range of plants due to its balance of drainage and nutrient retention.");

        } else if(p.getSoilType().equalsIgnoreCase("loam")){
            p.setSoilTypeRequirements("- Characteristics: Balanced mixture of sand, silt, and clay, well-drained, retains nutrients and moisture effectively.\n" +
                    "- Best for: Most plants, as it provides an ideal growing environment.");

        } else if (p.getSoilType().equalsIgnoreCase("peaty")) {
            p.setSoilTypeRequirements("- Characteristics: High organic matter, retains moisture, slightly acidic." +
                    "- Best for: Acid-loving plants and those that benefit from high organic content.");

        } else if (p.getSoilType().equalsIgnoreCase("saline")) {
            p.setSoilTypeRequirements("- Characteristics: High in soluble salts, can be challenging for plant growth.\n" +
                    "- Best for: Salt-tolerant plants.");

        }else {
            throw new ApiException("Invalid Soil type!");
        }
        plantsRepository.save(p);
        return p;
    }


    public Plants plantingSeason (Integer farmer_id , Integer plants_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants not found");
        }
        if(p.getSeason().equalsIgnoreCase("cool")){
            p.setPlantingSeason("Cool-Season Crops" +
                    "Spring Planting:" +
                    "- When to Plant: 4-6 weeks before the last expected frost date." +
                    "Fall Planting:" +
                    "- When to Plant: 8-10 weeks before the first expected frost date.");
        }else  if(p.getSeason().equalsIgnoreCase("warm")){
            p.setPlantingSeason("Spring Planting:" +
                    "- When to Plant: After the last frost date and soil has warmed up." +
                    "Summer Planting:" +
                    "- When to Plant: Early summer for fall harvest.");
        }else  if(p.getSeason().equalsIgnoreCase("tropical")){
            p.setPlantingSeason("Year-Round Planting:" +
                    "When to Plant: Can be planted throughout the year, but avoid extreme cold.");
        }else{
            throw new ApiException("Invalid Season Plants!");
        }
        plantsRepository.save(p);
        return p;
    }

    public Plants growthTime (Integer farmer_id , Integer plants_id) {
        Farmers farmers = farmersRepository.findFarmersById(farmer_id);
        if (farmers == null) {
            throw new ApiException("Farmer id not found");
        }
        Plants p = plantsRepository.findPlantsById(plants_id);
        if (p == null) {
            throw new ApiException("Plants id not found");
        }

        if(p.getGrowthSeason().equalsIgnoreCase("cool")){
            p.setGrowthTime("Cool-Season Plants" +

                    "- Short Growing Season:" +
                    "Characteristics: Quick to mature, often ready for harvest in 30-60 days.\n" +

                    "- Longer Growing Season:" +
                    "Characteristics: Some cool-season crops take longer to mature.");

        } else if(p.getGrowthSeason().equalsIgnoreCase("warm")){
            p.setGrowthTime("Warm-Season Plants" +

                    "- Short Growing Season:" +
                    "Characteristics: Some crops mature quickly, providing early yields."+

                    "- Longer Growing Season:" +
                    "Characteristics: Many warm-season crops take the full growing season to mature.");

        } else if(p.getGrowthSeason().equalsIgnoreCase("tropical")){
            p.setGrowthTime("Tropical Plants" +

                    "- Continuous Growth:" +
                    "Characteristics: Many tropical plants grow continuously, with staggered harvests." +

                    "- Seasonal Growth:" +
                    "Characteristics: Some tropical plants have specific growing seasons but generally thrive year-round.");
        }else{
            throw new ApiException("Invalid Growth Season!");
        }
        plantsRepository.save(p);
        return p;
    }

    public Set<Plants> getAllPlantsByCategory (String category){
        Set <Plants> plants = plantsRepository.findPlantsByCategory(category);
        if(plants.isEmpty()){
            throw new ApiException("Plants category:  "+category+ " not found");
        }
        return plants;
    }


    public Plants getMostPopularPlant() {
        List<Plants> allPlants = plantsRepository.findAll();

        Plants mostPopularPlant = null;
        int maxOrders = 4;

        for (Plants plant : allPlants) {
            int orderCount = plant.getOrders().size();
            if (orderCount > maxOrders) {
                maxOrders = orderCount;
                mostPopularPlant = plant;
            }
        }

        if (mostPopularPlant == null) {
            throw new ApiException("No orders found for any plants.");
        }

        return mostPopularPlant;
    }


}
