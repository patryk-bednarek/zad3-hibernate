package pl.edu.wszib.car.rent.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.car.rent.dao.IVehicleDAO;
import pl.edu.wszib.car.rent.model.Vehicle;
import pl.edu.wszib.car.rent.services.IRentService;
import pl.edu.wszib.car.rent.session.SessionObject;

import javax.annotation.Resource;

@Service
public class RentServiceImpl implements IRentService {

    @Autowired
    IVehicleDAO vehicleDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addVehicleByIdToRent(int id) {
        Vehicle vehicle = this.vehicleDAO.getVehicleById(id);
        for(Vehicle vehicleFromRent : this.sessionObject.getRent()) {
            if(vehicleFromRent.getId() == vehicle.getId()) {
                vehicleFromRent.setRent(vehicle.isRent());
                return;
            }
        }

        vehicle.setRent(true);
        this.sessionObject.getRent().add(vehicle);
    }

    @Override
    public void updateRent(Vehicle vehicle) {
        Vehicle vehicleFromDB = this.vehicleDAO.getVehicleById(vehicle.getId());
        vehicleFromDB.setRent(true);

        this.vehicleDAO.updateRent(vehicleFromDB);
    }


}
