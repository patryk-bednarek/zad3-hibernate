package pl.edu.wszib.car.rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rent.dao.IVehicleDAO;
import pl.edu.wszib.car.rent.model.Vehicle;
import pl.edu.wszib.car.rent.services.IVehicleService;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    IVehicleDAO vehicleDAO;

    @Override
    public Vehicle getVehicleById(int id) {
        return this.vehicleDAO.getVehicleById(id);
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Vehicle vehicleFromDB = this.vehicleDAO.getVehicleById(vehicle.getId());
        vehicleFromDB.setBrand(vehicle.getBrand());
        vehicleFromDB.setModel(vehicle.getModel());
        vehicleFromDB.setPlate(vehicle.getPlate());
        vehicleFromDB.setRent(vehicle.isRent());

        this.vehicleDAO.updateVehicle(vehicleFromDB);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return this.vehicleDAO.getAllVehicles();
    }

    @Override
    public void updateRent(Vehicle vehicle) {
        Vehicle vehicleFromDB = this.vehicleDAO.getVehicleById(vehicle.getId());
        vehicleFromDB.setRent(vehicle.isRent());

        this.vehicleDAO.updateVehicle(vehicleFromDB);
    }
}
