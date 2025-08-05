package com.fertilizer.system;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://127.0.0.1:5500") 
public class FertilizerOrderController 
{
    @Autowired
    private FertilizerOrderRepository orderRepository;

    @PostMapping
    public FertilizerOrder placeOrder(@RequestBody FertilizerOrder order)
    {
        order.setStatus("Pending");
        return orderRepository.save(order);
    }

    @GetMapping
    public List<FertilizerOrder> getAllOrders() 
    {
        return orderRepository.findAll();
    }

    @PutMapping("/{id}/status")
    public FertilizerOrder updateOrderStatus(@PathVariable Long id, @RequestBody String status)
    {
        FertilizerOrder order = orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status.replace("\"", ""));
        return orderRepository.save(order);
    }
}
