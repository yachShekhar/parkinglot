package com.shekhar.parkinglot.model;

import java.util.Objects;

public class Slot {
    private int id;
    private Vehicle vehicle;
    public Slot(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return vehicle == null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void removeVehicle(){
        this.setVehicle(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slot slot = (Slot) o;
        return id == slot.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

