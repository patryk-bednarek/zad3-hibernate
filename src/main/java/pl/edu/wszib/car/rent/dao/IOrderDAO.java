package pl.edu.wszib.car.rent.dao;

import pl.edu.wszib.car.rent.model.Order;

public interface IOrderDAO {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
