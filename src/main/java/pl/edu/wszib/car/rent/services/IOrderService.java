package pl.edu.wszib.car.rent.services;

import pl.edu.wszib.car.rent.model.Order;

public interface IOrderService {
    void saveOrder(Order order);
    Order getOrderById(int id);
}
