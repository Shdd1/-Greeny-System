package com.example.c3.Controller;


import com.example.c3.Api.ApiResponse;
import com.example.c3.Model.Orders;
import com.example.c3.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;


    @GetMapping("/get")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.status(200).body(ordersService.getAllOrders());
    }

    @PostMapping("/add/{users_id}/{plants_id}")
    public ResponseEntity addOrder(@Valid @RequestBody Orders orders, @PathVariable Integer users_id, @PathVariable Integer plants_id) {
        ordersService.addOrder(orders, users_id, plants_id);
        return ResponseEntity.status(200).body(new ApiResponse("Order successfully added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id, @Valid @RequestBody Orders orders) {
        ordersService.updateOrder(id, orders);
        return ResponseEntity.status(200).body(new ApiResponse("Order successfully updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("Order successfully deleted"));
    }

    @PutMapping("/applyDiscount/{orderId}")
    public ResponseEntity applyDiscountToOrder(@PathVariable Integer orderId, @RequestParam double discountPercentage) {
        ordersService.applyDiscountToOrder(orderId, discountPercentage);
        return ResponseEntity.status(200).body(new ApiResponse("Discount applied successfully"));

    }
}