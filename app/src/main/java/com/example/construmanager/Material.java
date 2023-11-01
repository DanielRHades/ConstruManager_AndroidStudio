package com.example.construmanager;

public class Material {
    String projectId,name,amountMissing,amountAvailable,amountPayed,amountOwed,price;
    public Material() {}
    public Material(String projectId, String name, String price) {
        this.projectId = projectId;
        this.name = name;
        this.price = price;
    }
    public Material(String amountAvailable, String amountMissing, String amountPayed, String amountOwed) {
        this.amountAvailable = amountAvailable;
        this.amountMissing = amountMissing;
        this.amountPayed = amountPayed;
        this.amountOwed = amountOwed;
    }
    public String getAmountMissing() {
        return amountMissing;
    }

    public void setAmountMissing(String amountMissing) {
        this.amountMissing = amountMissing;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(String amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public String getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(String amountPayed) {
        this.amountPayed = amountPayed;
    }

    public String getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(String amountOwed) {
        this.amountOwed = amountOwed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
