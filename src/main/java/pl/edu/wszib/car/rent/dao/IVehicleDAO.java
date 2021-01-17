package pl.edu.wszib.car.rent.dao;

import pl.edu.wszib.car.rent.model.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    Vehicle getVehicleById(int id);
    void updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    void updateRent(Vehicle vehicle);
}
