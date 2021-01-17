package pl.edu.wszib.car.rent.services;

import pl.edu.wszib.car.rent.model.Vehicle;
import java.util.List;


public interface IVehicleService {
    Vehicle getVehicleById(int id);
    void updateVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
    void updateRent(Vehicle vehicle);
}
