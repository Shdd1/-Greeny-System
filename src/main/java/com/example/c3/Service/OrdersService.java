package com.example.c3.Service;

import com.example.c3.Api.ApiException;
import com.example.c3.Model.Orders;
import com.example.c3.Model.Plants;
import com.example.c3.Model.Users;
import com.example.c3.Repository.OrdersRepository;
import com.example.c3.Repository.PlantsRepository;
import com.example.c3.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersRepository usersRepository;
    private final PlantsRepository plantsRepository;

    private static final double DISCOUNT_PERCENTAGE = 10.0;


    public List<Orders> getAllOrders() {

        return ordersRepository.findAll();
    }


    public void addOrder(Orders orders, Integer users_id, Integer plants_id) {
        Users users = usersRepository.findUsersById(users_id);
        Plants plants = plantsRepository.findPlantsById(plants_id);
        if (users == null || plants == null) {
            throw new ApiException("Cannot assign user and plant to orders");
        }
        if (plants.getStock() < orders.getQuantity()) {
            throw new ApiException("Insufficient stock for plant: " + plants.getName());
        }


        plants.setStock(plants.getStock() - orders.getQuantity());


        orders.setUsers(users);

        orders.getPlants().add(plants);
        plants.getOrders().add(orders);

        double totalAmount = plants.getPrice() * orders.getQuantity();
        orders.setTotalAmount(totalAmount);

        long userOrderCount = ordersRepository.countByUsersId(users_id);
        if (userOrderCount >= 3) {
            double discount = totalAmount * (DISCOUNT_PERCENTAGE / 100);
            orders.setTotalAmount(totalAmount - discount);
        }
        orders.setOrderDate(LocalDate.now());
        orders.setUpdatedAt(LocalDate.now());
        ordersRepository.save(orders);
    }

    public void updateOrder(Integer id, Orders orders) {
        Orders order1 = ordersRepository.findOrdersById(id);

        if (order1 == null) {
            throw new ApiException("Order id not found");
        }

        order1.setTotalAmount(orders.getTotalAmount());
        order1.setItems(orders.getItems());
        order1.setQuantity(orders.getQuantity());
        order1.setToolStatus(orders.isToolStatus());
        order1.setUpdatedAt(LocalDate.now());
        ordersRepository.save(order1);
    }


    public void deleteOrder(Integer id) {
        Orders order1 = ordersRepository.findOrdersById(id);
        if (order1 == null) {
            throw new ApiException("Order id not found");
        }
        ordersRepository.delete(order1);
    }

    public void applyDiscountToOrder(Integer orderId, double discountPercentage) {
        Orders order = ordersRepository.findOrdersById(orderId);
        if (order == null) {
            throw new ApiException("Order not found");
        }

        double discountAmount = order.getTotalAmount() * (discountPercentage / 100);
        double newTotalAmount = order.getTotalAmount() - discountAmount;

        order.setDiscount(discountAmount);
        order.setTotalAmount(newTotalAmount);
        order.setUpdatedAt(LocalDate.now());

        ordersRepository.save(order);


    }
}
