package pl.edu.wszib.car.rent.dao;


import pl.edu.wszib.car.rent.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean persistUser(User user);
}
