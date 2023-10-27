package com.example.construmanager;

public class Worker {
    private String id, name, occupation, email;

    Worker ()
    {

    }

    public Worker(String name, String occupation, String email) {
        this.name = name;
        this.occupation = occupation;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
