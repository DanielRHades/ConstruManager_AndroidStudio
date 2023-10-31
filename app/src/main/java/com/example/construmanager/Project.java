package com.example.construmanager;

public class Project {
    String id, name, company, address, affiliates;
    public Project(){
    }
    public Project(String id, String name, String company, String address, String affiliates) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.address = address;
        this.affiliates = affiliates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(String affiliates) {
        this.affiliates = affiliates;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
