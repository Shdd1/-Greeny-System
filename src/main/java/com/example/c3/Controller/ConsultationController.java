package com.example.c3.Controller;

import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Consultation;
import com.example.c3.Service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/consultation")
@RequiredArgsConstructor
public class ConsultationController {
    private final ConsultationService consultationService;

    @GetMapping("/get")
    public ResponseEntity getAllConsultations(){
        return ResponseEntity.status(200).body(consultationService.getAllConsultations());
    }

    @PostMapping("/add/{consultant_id}/{user_id}")
    public ResponseEntity addConsultation(@PathVariable Integer consultant_id, @PathVariable Integer user_id,@Valid @RequestBody Consultation consultation){
        consultationService.addNewConsultation(consultation,consultant_id,user_id);
        return ResponseEntity.status(200).body(new ApiResponse("Consultation successfully added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateConsultation (@PathVariable Integer id , @Valid @RequestBody Consultation consultation){
        consultationService.updateConsultation(id, consultation);
        return ResponseEntity.status(200).body(new ApiResponse("Consultation successfully updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteConsultation(@PathVariable Integer id){
        consultationService.deleteConsultation(id);
        return ResponseEntity.status(200).body(new ApiResponse("Consultation successfully deleted"));
    }
    //////////////////
    @PutMapping("/complete/consultation/{consultationid}/consult/{consultatid}/user/{userId}")
    public ResponseEntity ChangeStatusToCompleted(@PathVariable Integer consultationid,@PathVariable Integer consultatid,@PathVariable Integer userId){
        consultationService.ChangeStatusToCompleted(consultationid,consultatid,userId);
        return ResponseEntity.status(200).body(new ApiResponse("Your consultation has been Completed."));
    }
    /////////////////////
    @PutMapping("/extra_duration/{userId}/{consultationId}/{additionalDuration}")
    public ResponseEntity extendConsultationDuration(@PathVariable Integer userId,@PathVariable Integer consultationId,@PathVariable double additionalDuration){
     consultationService.extendConsultationDuration(userId,consultationId,additionalDuration);
     return ResponseEntity.status(200).body(new ApiResponse("The duration has been updated successfully."));
    }
    ////////////////////
    @PutMapping("/canceled/user_id/{userId}/con_id/{conId}")
     public ResponseEntity canceledStatusOfConsultation(@PathVariable Integer userId,@PathVariable Integer conId){
        consultationService.UsercanceledStatusOfConsultation(userId,conId);
        return ResponseEntity.status(200).body(new ApiResponse("user canceled"));
     }

}
