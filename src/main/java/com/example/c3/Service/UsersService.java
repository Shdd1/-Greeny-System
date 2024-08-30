package com.example.c3.Service;


import com.example.c3.Api.ApiException;
import com.example.c3.Model.*;
import com.example.c3.Repository.ConsultationRepository;
import com.example.c3.Repository.NurseriesRepository;
import com.example.c3.Repository.PlansRepository;
import com.example.c3.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PlansRepository plansRepository;
    private final ConsultationRepository consultationRepository;
private final NurseriesRepository nurseriesRepository;
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void addUsers(Users users) {
        users.setRegistrationDate(LocalDate.now());
        users.setClientConfirmed(false);
        usersRepository.save(users);
    }

    public void updateUsers(Integer id, Users users) {
        Users user = usersRepository.findUsersById(id);
        if (user == null) {
            throw new ApiException("not found");
        }
        user.setUsername(users.getUsername());
        user.setEmail(users.getEmail());
        user.setAddress(users.getAddress());
        user.setPassword(users.getPassword());
        user.setPhone_number(users.getPhone_number());
        user.setPreferences(users.getPreferences());
        //user.setRegistrationDate(users.getRegistrationDate());
        usersRepository.save(user);

    }

    public void deleteUsers(Integer id) {
        Users users = usersRepository.findUsersById(id);
        if (users == null) {
            throw new ApiException("user not found");
        }
        usersRepository.delete(users);
    }


    // many to many : assign plan to user
    public void assignPlanToUser(Integer planId, Integer userId) {
        Plans plans = plansRepository.findPlansById(planId);
        Users users = usersRepository.findUsersById(userId);
        if (plans == null || users == null) {
            throw new ApiException("not found");

        }
        plans.getUsers().add(users);
        users.getPlans().add(plans);

        plansRepository.save(plans);
        usersRepository.save(users);

    }

    //6
    public void userConfirmed(Integer id, Integer bookingId) {
        Users users = usersRepository.findUsersById(id);
        Consultation consultation = consultationRepository.findConsultationById(bookingId);

        if (users == null || consultation == null) {
            throw new ApiException("User or consultation not found");
        }

        if(!consultation.getUsers().equals(users)){
            throw new ApiException("User has no consultation") ;
        }

        if (consultation.getStatus().equalsIgnoreCase("completed")) {
            throw new ApiException("It is finished.");
        }
        if (consultation.getStatus().equalsIgnoreCase("PENDING")) {
            throw new ApiException("The consultation was not approved.");
        }
        if (consultation.getStatus().equalsIgnoreCase("Canceled")) {
            throw new ApiException("The consultation is already Canceled.");
        }
        if (users.getClientConfirmed().equals(true)) {
            throw new ApiException("The session has been confirmed.");
        }
        users.setClientConfirmed(true);
        usersRepository.save(users);

    }

    public Plans getPlanRecommendationByPlantName(String plantName) {
        Plans plan = plansRepository.findPlansByPlants(plantName);
        if (plan == null) {
            throw new ApiException("No recommendation found for the plant: " + plantName);
        }
        return plan;
    }
    public List<Nurseries> getAllNurseries() {
        List <Nurseries> nurseries=nurseriesRepository.findAll();
        if(nurseries.isEmpty()){
            throw new ApiException("Nurseries not found");
        }
        return nurseries;
    }

    public List<Nurseries> getNurseriesByLocation(String location) {
        List <Nurseries> nurseriesByLocation = nurseriesRepository.findNurseriesByLocation(location);
        if(nurseriesByLocation.isEmpty()){
            throw new ApiException("Nurseries by location: "+location+" not found");
        }
        return nurseriesByLocation;

}

}
