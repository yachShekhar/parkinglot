package com.shekhar.parkinglot.service;

import com.shekhar.parkinglot.model.Car;
import com.shekhar.parkinglot.model.Slot;
import com.shekhar.parkinglot.model.Vehicle;
import com.shekhar.parkinglot.repository.ParkingRepository;
import com.shekhar.parkinglot.repository.ParkingRepositoryImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public enum CommandType implements CommandOps {
    create_parking_lot{
        public void apply(List<String> input) {

           ParkingRepository parkingRepository = new ParkingRepositoryImpl();
           parkingRepository.createSlot(Integer.valueOf(input.get(0)));
        }
    },
    park{
        public void apply(List<String> input) {
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            Vehicle vehicle = new Car(input.get(0), input.get(1));
            parkingRepository.park(vehicle);
        }
    },
    leave{
        public void apply(List<String> input) {
            int leaveSlotNo = Integer.valueOf(input.get(0));
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            parkingRepository.leaveSlot(leaveSlotNo);
        }
    },
    status{
        public void apply(List<String> input) {
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            parkingRepository.status();
        }
    },
    registration_numbers_for_cars_with_colour {
        public void apply(List<String> input) {
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            Set<Vehicle> vehicles = parkingRepository.findCarsByItsColor(input.get(0));
            if(vehicles != null){
                Iterator<Vehicle> vehicleIterator = vehicles.iterator();
                while (vehicleIterator.hasNext()){
                    Vehicle vehicle = vehicleIterator.next();
                    System.out.print(vehicle.getRegistrationNumber());
                    if(vehicleIterator.hasNext()){
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
    },
    slot_numbers_for_cars_with_colour {
        public void apply(List<String> input) {
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            Set<Slot> slots = parkingRepository.findSlotsByCarColor(input.get(0));
            if(slots != null){
                Iterator<Slot> slotIterator = slots.iterator();
                while (slotIterator.hasNext()){
                    Slot slot = slotIterator.next();
                    System.out.print(slot.getId());
                    if(slotIterator.hasNext()){
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }
    },
    slot_number_for_registration_number {
        public void apply(List<String> input) {
            ParkingRepository parkingRepository = new ParkingRepositoryImpl();
            Slot slot = parkingRepository.findByRegistrationNumber(input.get(0));
            if (slot != null){
                System.out.println(slot.getId());
            }else{
                System.err.println("Not found");
            }
        }
    };
}
