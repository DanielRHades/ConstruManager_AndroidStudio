package com.example.construmanager;

public class Material {
    private String projectId,materialId,name,amountMissing,amountAvailable,amountPayed,amountOwed;
    private int price;
    public Material() {

    }
    public Material(String projectId, String materialId, String name, int price) {
        this.projectId = projectId;
        this.materialId = materialId;
        this.name = name;
        this.price = price;
    }
    public Material(String projectId, String materialId, String name, int price, String amountAvailable, String amountMissing, String amountPayed, String amountOwed) {
        this.projectId = projectId;
        this.materialId = materialId;
        this.name = name;
        this.price = price;
        this.amountAvailable = amountAvailable;
        this.amountMissing = amountMissing;
        this.amountPayed = amountPayed;
        this.amountOwed = amountOwed;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAmountMissing() {
        return amountMissing;
    }

    public void setAmountMissing(String amountMissing) {
        this.amountMissing = amountMissing;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
