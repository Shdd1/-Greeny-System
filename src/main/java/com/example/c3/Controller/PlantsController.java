package com.example.c3.Controller;


import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Plants;
import com.example.c3.Service.PlantsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plants")
public class PlantsController {
    private final PlantsService plantsService;

    @GetMapping("/get")
    public ResponseEntity getAllPlants() {
        return ResponseEntity.ok(plantsService.getAllPlants());
    }

    @PostMapping("/add/fid/{farmer_id}/nid/{nurseries_id}")
    public ResponseEntity addPlants(@PathVariable Integer farmer_id ,@PathVariable Integer nurseries_id,@Valid @RequestBody Plants plants) {
        plantsService.addPlant(plants,farmer_id,nurseries_id);
        return ResponseEntity.status(200).body(new ApiResponse("Plants added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePlants(@Valid @RequestBody Plants plants, @PathVariable Integer id) {
        plantsService.updatePlant(plants, id);
        return ResponseEntity.status(200).body(new ApiResponse("Plants updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlants(@PathVariable Integer id) {
        plantsService.deletePlants(id);
        return ResponseEntity.status(200).body(new ApiResponse("Plants deleted successfully"));
    }

    @GetMapping("/light/{farmer_id}/{plants_id}")
    public ResponseEntity lightRequirements (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.lightRequirements(farmer_id,plants_id));
    }

    @GetMapping("/water/{farmer_id}/{plants_id}")
    public ResponseEntity waterRequirements (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.waterRequirements(farmer_id,plants_id));
    }

    @GetMapping("/temp/{farmer_id}/{plants_id}")
    public ResponseEntity temperatureRequirements (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.temperatureRequirements(farmer_id,plants_id));
    }


    @GetMapping("/soil/{farmer_id}/{plants_id}")
    public ResponseEntity soilTypeRequirements (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.soilTypeRequirements(farmer_id,plants_id));
    }

    @GetMapping("/season/{farmer_id}/{plants_id}")
    public ResponseEntity plantingSeason (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.plantingSeason(farmer_id,plants_id));
    }

    @GetMapping("/growth/{farmer_id}/{plants_id}")
    public ResponseEntity growthTime (@PathVariable Integer farmer_id,@PathVariable Integer plants_id) {
        return ResponseEntity.status(200).body(plantsService.growthTime(farmer_id,plants_id));
    }

    @GetMapping("/getCategory/{category}")
    public ResponseEntity getAllPlantsByCategory (@PathVariable String category) {
        return ResponseEntity.status(200).body(plantsService.getAllPlantsByCategory(category));
    }

    @GetMapping("/mostPopular")
    public ResponseEntity getMostPopularPlant() {
        Plants mostPopularPlant = plantsService.getMostPopularPlant();
        return ResponseEntity.status(200).body(mostPopularPlant);

    }

}