package com.shekhar.parkinglot.repository;

import com.shekhar.parkinglot.model.Slot;
import com.shekhar.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Set;

public interface ParkingRepository {

    Set<Vehicle> findCarsByItsColor(String color);

    Slot findByRegistrationNumber(String registrationNo);

    Set<Slot> findSlotsByCarColor(String color);

    void leaveSlot(int slotId);

    void park(Vehicle vehicle);

    void createSlot(int slotCount);

    void status();
}
