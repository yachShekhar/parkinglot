package com.shekhar.parkinglot.model;

public abstract class Vehicle {
    private String registrationNumber;
    private String color;

    protected Vehicle(String registrationNumber, String color){
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}
