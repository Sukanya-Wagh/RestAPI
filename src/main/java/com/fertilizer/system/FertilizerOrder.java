package com.fertilizer.system;
import jakarta.persistence.*;
@Entity
public class FertilizerOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String farmerName;
    private String fertilizerType;
    private int quantity;
    private Double price;
    private String status;

    public FertilizerOrder() {}

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFarmerName()
    {
        return farmerName;
    }

    public void setFarmerName(String farmerName)
    {
        this.farmerName = farmerName;
    }

    public String getFertilizerType() 
    {
        return fertilizerType;
    }

    public void setFertilizerType(String fertilizerType)
    {
        this.fertilizerType = fertilizerType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }

    public Double getPrice() 
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
