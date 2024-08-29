package com.example.c3.Controller;

import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.GardeningTools;
import com.example.c3.Service.GardeningToolsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tools")
@RequiredArgsConstructor
public class GardeningToolsController {
    private final GardeningToolsService gardeningToolsService;

    @GetMapping("/get")
    public ResponseEntity getGardeningTools(){
        return ResponseEntity.status(200).body(gardeningToolsService.getAllGardeningTools());
    }
    @PostMapping("/add")
    public ResponseEntity addGardeningTools(@Valid @RequestBody GardeningTools gardeningTools){

        gardeningToolsService.addGardeningTools(gardeningTools);
        return ResponseEntity.status(200).body(new ApiResponse("is added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateGardeningTools(@PathVariable Integer id,@Valid@RequestBody GardeningTools gardeningTools){

        gardeningToolsService.updateGardeningTools(id,gardeningTools);
        return ResponseEntity.status(200).body(new ApiResponse("is Updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteGardeningTools(@PathVariable Integer id){
        gardeningToolsService.deleteGardeningTools(id);
        return ResponseEntity.status(200).body(new ApiResponse("is deleted"));
    }

    //assign Tools To Orders
    @PutMapping ("/assign/toolsId/{toolsId}/orderId/{orderId}")
    public ResponseEntity assignPlanToUser(@PathVariable Integer toolsId,@PathVariable Integer orderId) {
        gardeningToolsService.assignToolsToOrders(toolsId, orderId);
        return ResponseEntity.status(200).body(new ApiResponse("Successful assign"));
    }

}
