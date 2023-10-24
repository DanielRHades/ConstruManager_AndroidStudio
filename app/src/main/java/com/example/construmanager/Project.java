package com.example.construmanager;

import java.util.ArrayList;
import java.util.List;

public class Project {
    String name, company, address;
    List<String> affiliates = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(List<String> affiliates) {
        this.affiliates = affiliates;
    }
}
