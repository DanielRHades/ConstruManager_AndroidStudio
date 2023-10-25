package com.example.construmanager;

public class Material {
    String name;
    Integer amountAvailable,amountPayed,amountOwed;
    Double price;

    public Material(String name, Integer amountAvailable, Double price) {
        this.name = name;
        this.amountAvailable = amountAvailable;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(Integer amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public Integer getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(Integer amountPayed) {
        this.amountPayed = amountPayed;
    }

    public Integer getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(Integer amountOwed) {
        this.amountOwed = amountOwed;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
