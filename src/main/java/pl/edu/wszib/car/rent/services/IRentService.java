package pl.edu.wszib.car.rent.services;

import pl.edu.wszib.car.rent.model.Vehicle;

public interface IRentService {
    void addVehicleByIdToRent(int id);
    void updateRent(Vehicle vehicle);
}
