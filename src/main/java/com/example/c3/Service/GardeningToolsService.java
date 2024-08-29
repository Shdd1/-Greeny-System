package com.example.c3.Service;

import com.example.c3.Api.ApiException;
import com.example.c3.Model.GardeningTools;
import com.example.c3.Model.Orders;
import com.example.c3.Repository.GardeningToolsRepository;
import com.example.c3.Repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GardeningToolsService {

    private final GardeningToolsRepository gardeningToolsRepository;
    private final OrdersRepository ordersRepository;


    public List<GardeningTools> getAllGardeningTools() {
        return gardeningToolsRepository.findAll();
    }

    public void addGardeningTools(GardeningTools gardeningTools) {
        gardeningToolsRepository.save(gardeningTools);
    }

    public void updateGardeningTools(Integer id, GardeningTools gardeningTools) {
        GardeningTools gardeningTools1 = gardeningToolsRepository.findGardeningToolsById(id);
        if (gardeningTools1 == null) {
            throw new ApiException("not found");
        }
        gardeningTools1.setPrice(gardeningTools.getPrice());
        gardeningTools1.setItems(gardeningTools.getItems());
        gardeningToolsRepository.save(gardeningTools1);


    }

    public void deleteGardeningTools(Integer id) {
        GardeningTools gardeningTools = gardeningToolsRepository.findGardeningToolsById(id);
        if (gardeningTools == null) {
            throw new ApiException("user not found");
        }
        gardeningToolsRepository.delete(gardeningTools);
    }


    //Many to many
    public void assignToolsToOrders(Integer toolsId,Integer orderId){
        Orders orders=ordersRepository.findOrdersById(orderId);
        GardeningTools gardeningTools=gardeningToolsRepository.findGardeningToolsById(toolsId);
        if(orders==null || gardeningTools==null){
            throw new ApiException("Cannot assign tools to orders");
        }

        orders.getGardeningTools().add(gardeningTools);
        orders.setToolStatus(true);

        double newTotalAmount = orders.getTotalAmount() + gardeningTools.getPrice();
        orders.setTotalAmount(newTotalAmount);

        gardeningTools.getOrders().add(orders);
        ordersRepository.save(orders);
        gardeningToolsRepository.save(gardeningTools);

    }
}