package com.example.c3.Controller;


import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Nurseries;
import com.example.c3.Service.NurseriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nurseries")
@RequiredArgsConstructor
public class NurseriesController {
    private final NurseriesService nurseriesService;

    @GetMapping("/get")
    public ResponseEntity getAllNurseries(){
        return ResponseEntity.status(200).body(nurseriesService.getAllNurseries());
    }
    @PostMapping("/add/{farmer_id}")
    public ResponseEntity addNurseries(@Valid @RequestBody Nurseries nurseries, @PathVariable Integer farmer_id){
        nurseriesService.addNurseries(nurseries,farmer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Nurseries add successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateNurseries(@Valid @RequestBody Nurseries nurseries, @PathVariable Integer id){
        nurseriesService.updateNurseries(nurseries,id);
        return ResponseEntity.status(200).body("Nurseries update successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNurseries(@PathVariable Integer id){
        nurseriesService.deleteNurseries(id);
        return ResponseEntity.status(200).body("Nurseries delete successfully");
    }
@GetMapping("/getListOfPlants/{nurseries_id}")
    public ResponseEntity getListOfPlantsToNurseries(@PathVariable Integer nurseries_id){
        return ResponseEntity.status(200).body(nurseriesService.getListOfPlantsToNurseries(nurseries_id));
    }
    @GetMapping("/availableStock/{plant_id}")
    public ResponseEntity getNurseriesWithAvailableStock(@PathVariable Integer plant_id) {
        return ResponseEntity.status(200).body(nurseriesService.getNurseriesWithAvailablePlantStock(plant_id));
    }

}
