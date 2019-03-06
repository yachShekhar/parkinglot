package com.shekhar.parkinglot.repository;

import com.shekhar.parkinglot.model.Slot;
import com.shekhar.parkinglot.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingRepositoryImpl implements ParkingRepository {
    @Override
    public Set<Vehicle> findCarsByItsColor(String color) {
        return ParkingDataStorage.COLOR_SLOTS_DATA.get(color).stream().filter(slot -> !slot.isAvailable()).map(Slot::getVehicle).collect(Collectors.toSet());
    }

    @Override
    public Slot findByRegistrationNumber(String registrationNo) {
        return ParkingDataStorage.REGISTRATION_NUMBER_SLOT_DATA.get(registrationNo);
    }

    @Override
    public Set<Slot> findSlotsByCarColor(String color) {
        return ParkingDataStorage.COLOR_SLOTS_DATA.get(color);
    }

    @Override
    public void leaveSlot(int slotId) {
        Optional<Slot> optionalSlot = ParkingDataStorage.SLOTS.stream().filter(e -> e.getId() == slotId).findFirst();
        if(optionalSlot.isPresent()) {
            Slot slot = optionalSlot.get();
            if(!slot.isAvailable()) {
                Vehicle vehicle = slot.getVehicle();
                ParkingDataStorage.COLOR_SLOTS_DATA.remove(vehicle.getColor()).remove(slot);
                ParkingDataStorage.REGISTRATION_NUMBER_SLOT_DATA.remove(vehicle.getRegistrationNumber());
                slot.removeVehicle();
            }else{
                System.err.println("Slot is already empty.");
            }
        }else{
            System.err.println("Invalid Slot:"+slotId);
        }
    }

    @Override
    public void park(Vehicle vehicle) {
        Optional<Slot> optionalSlot = ParkingDataStorage.SLOTS.stream().filter(e -> e.isAvailable()).findFirst();
        if(optionalSlot.isPresent()){
            Slot slot = optionalSlot.get();
            slot.setVehicle(vehicle);

            if(!ParkingDataStorage.COLOR_SLOTS_DATA.containsKey(vehicle.getColor())){
                ParkingDataStorage.COLOR_SLOTS_DATA.put(vehicle.getColor(), new HashSet<>());
            }
            ParkingDataStorage.COLOR_SLOTS_DATA.get(vehicle.getColor()).add(slot);
            ParkingDataStorage.REGISTRATION_NUMBER_SLOT_DATA.put(vehicle.getRegistrationNumber(), slot);
            System.out.println("Allocated slot number: "+slot.getId());
        }else{
            System.out.println("Sorry, parking lot is full");
        }
    }

    @Override
    public void createSlot(int slotCount) {
        List<Slot> slots = new ArrayList<>();
        for(int i = 1; i <= slotCount; i++){
            Slot slot = new Slot(i);
            slots.add(slot);
        }
        ParkingDataStorage.SLOTS.addAll(slots);
        System.out.println("Created a parking lot with "+slotCount+" slots");
    }

    @Override
    public void status() {
        System.out.println("Slot No.\tRegistration No\tColour");
        ParkingDataStorage.SLOTS.stream().filter(slot -> !slot.isAvailable()).forEach(slot -> {
            System.out.println(slot.getId()+ "\t"+ slot.getVehicle().getRegistrationNumber()+"\t"+slot.getVehicle().getColor());
        });
    }
}
