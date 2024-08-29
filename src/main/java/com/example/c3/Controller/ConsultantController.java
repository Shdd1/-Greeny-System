package com.example.c3.Controller;

import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Consultant;
import com.example.c3.Service.ConsultantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/consultant")
@RequiredArgsConstructor
public class ConsultantController {
    private final ConsultantService consultantService;


    @GetMapping("/get")
    public ResponseEntity getAllConsultants(){
        return ResponseEntity.status(200).body(consultantService.getAllConsultants());
    }

    @PostMapping("/add")
    public ResponseEntity addConsultants(@Valid @RequestBody Consultant consultant){
        consultantService.addNewConsultant(consultant);
        return ResponseEntity.status(200).body(new ApiResponse("Consultant successfully added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateConsultant (@PathVariable Integer id ,@Valid @RequestBody Consultant consultant){
        consultantService.updateConsultant(id,consultant);
        return ResponseEntity.status(200).body(new ApiResponse("Consultant successfully updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteConsultant(@PathVariable Integer id){
        consultantService.deleteConsultant(id);
        return ResponseEntity.status(200).body(new ApiResponse("Consultant successfully deleted"));
    }
    /////////////////////////////////
    @PutMapping("/Schedule/consultation_id/{consultation_id}/consultant_id/{consultant_id}/status/{status}")
    public ResponseEntity confirmConsultationSchedule(@PathVariable Integer consultation_id,@PathVariable Integer consultant_id,@PathVariable String status){
        consultantService.confirmConsultationSchedule(consultation_id,consultant_id,status);
        return ResponseEntity.status(200).body(new ApiResponse("Your consultation has been scheduled."));
    }
    //////////////////////////////////
    @PutMapping("/confirmed/{consultantId}/{bookingId}")
    public ResponseEntity consultantConfirmed(@PathVariable Integer consultantId,@PathVariable Integer bookingId){
        consultantService.consultantConfirmed(consultantId,bookingId);
        return ResponseEntity.status(200).body(new ApiResponse("The session has been confirmed to be over."));
    }
    /////////////////////////////////
    @PutMapping("/canceled/consultant_id/{consultantId}/consultation-id/{consultationId}")
    public ResponseEntity canceledStatusOfConsultation(@PathVariable Integer consultantId,@PathVariable Integer consultationId){
        consultantService.canceledStatusOfConsultation(consultantId,consultationId);
        return ResponseEntity.status(200).body(new ApiResponse("The session was cancelled by the consultant."));
    }

}
