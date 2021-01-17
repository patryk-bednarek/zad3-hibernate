package pl.edu.wszib.car.rent.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rent.dao.IVehicleDAO;
import pl.edu.wszib.car.rent.model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDAOImpl implements IVehicleDAO {

    @Autowired
    Connection connection;

    @Override
    public Vehicle getVehicleById(int id) {
        String sql = "SELECT * FROM cvehicles WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new Vehicle(resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("plate"),
                        resultSet.getBoolean("rent"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE cvehicles SET brand = ?, model = ?, plate = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getPlate());
            preparedStatement.setInt(4, vehicle.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cvehicles;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vehicles.add(new Vehicle(resultSet.getInt("id"),
                                    resultSet.getString("brand"),
                                    resultSet.getString("model"),
                                    resultSet.getString("plate"),
                                    resultSet.getBoolean("rent")));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return vehicles;
    }

    @Override
    public void updateRent(Vehicle vehicle) {
        String sql = "UPDATE cvehicles SET rent = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, vehicle.isRent());
            preparedStatement.setInt(2,vehicle.getId());


            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
