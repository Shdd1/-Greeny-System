package com.example.c3.Controller;

import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Farmers;
import com.example.c3.Service.FarmersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
public class FarmersController {
    private final FarmersService farmersService;

    @GetMapping("/get")
    public ResponseEntity getAllFarmers() {
        return ResponseEntity.status(200).body(farmersService.getAllFarmers());
    }
    @PostMapping("/add")
    public ResponseEntity addFarmers(@Valid @RequestBody Farmers farmers) {
        farmersService.addFarmer(farmers);
        return ResponseEntity.status(200).body(new ApiResponse("Farmer added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateFarmers(@Valid @RequestBody Farmers farmers, @PathVariable Integer id){
        farmersService.updateFarmer(farmers,id);
        return ResponseEntity.status(200).body(new ApiResponse("Farmer updated successfully"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFarmers(@PathVariable Integer id){
        farmersService.deleteFarmer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Farmer deleted successfully"));
    }
    @GetMapping("/byLocation/{location}")
    public ResponseEntity getFarmersByLocation(@PathVariable String location) {
        List<Farmers> farmers = farmersService.getFarmersByLocation(location);
        return ResponseEntity.status(200).body(farmers);
    }
}
