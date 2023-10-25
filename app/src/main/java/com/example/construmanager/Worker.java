package com.example.construmanager;

public class Worker {
    String name, occupation, rank;

    public Worker(String name, String occupation, String rank) {
        this.name = name;
        this.occupation = occupation;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
