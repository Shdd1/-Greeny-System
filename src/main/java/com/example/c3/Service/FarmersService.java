package com.example.c3.Service;

import com.example.c3.Api.ApiException;
import com.example.c3.Model.Farmers;
import com.example.c3.Repository.FarmersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmersService {

    private final FarmersRepository farmersRepository;

    public List<Farmers> getAllFarmers() {
        return farmersRepository.findAll();
    }

    public void addFarmer(Farmers farmers) {
        farmers.setRegistration_date(LocalDate.now());
        farmersRepository.save(farmers);
    }

    public void updateFarmer(Farmers farmers, Integer id) {
        Farmers farmers1 = farmersRepository.findFarmersById(id);
        if (farmers1 == null) {
            throw new ApiException("Farmer not found");
        }
        farmers1.setName(farmers.getName());
        farmers1.setAddress(farmers.getAddress());
        farmers1.setPhone_number(farmers.getPhone_number());
        farmers1.setRegistration_date(farmers.getRegistration_date());

        farmersRepository.save(farmers1);
    }

    public void deleteFarmer(Integer id) {
        Farmers farmers1 = farmersRepository.findFarmersById(id);
        if (farmers1 == null) {
            throw new ApiException("Farmer not found");
        }
        farmersRepository.delete(farmers1);
    }
    public List<Farmers> getFarmersByLocation(String location) {
        return farmersRepository.findFarmersByLocation(location);

    }

}
