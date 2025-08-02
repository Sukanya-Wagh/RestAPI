package com.fertilizer.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FertilizerOrderService
{

    @Autowired
    private FertilizerOrderRepository repo;

    public List<FertilizerOrder> getAllOrders()
    {
        return repo.findAll();
    }

    public FertilizerOrder saveOrder(FertilizerOrder order)
    {
        return repo.save(order);
    }

    public FertilizerOrder updateOrderStatus(Long id, String status)
    {
        FertilizerOrder order = repo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return repo.save(order);
    }
}
