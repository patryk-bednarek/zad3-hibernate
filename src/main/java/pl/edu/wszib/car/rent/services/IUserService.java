package pl.edu.wszib.car.rent.services;

import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);
    void logout();
    boolean register(RegistrationModel registrationModel);
}
