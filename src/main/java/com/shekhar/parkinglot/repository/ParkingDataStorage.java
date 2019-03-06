package com.shekhar.parkinglot.repository;

import com.shekhar.parkinglot.model.Slot;
import com.shekhar.parkinglot.model.Vehicle;

import java.util.*;

class ParkingDataStorage {
    static Map<String, Set<Slot>> COLOR_SLOTS_DATA = new HashMap<>();
    static Map<String, Slot> REGISTRATION_NUMBER_SLOT_DATA = new HashMap<>();
    static List<Slot> SLOTS = new ArrayList<>();
}
