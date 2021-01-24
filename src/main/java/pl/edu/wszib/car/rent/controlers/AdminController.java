package pl.edu.wszib.car.rent.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rent.model.Order;
import pl.edu.wszib.car.rent.model.OrderPosition;
import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.model.Vehicle;
import pl.edu.wszib.car.rent.services.IOrderService;
import pl.edu.wszib.car.rent.services.IVehicleService;
import pl.edu.wszib.car.rent.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IVehicleService vehicleService;

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Vehicle vehicle = this.vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@ModelAttribute Vehicle vehicle) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.vehicleService.updateVehicle(vehicle);

        return "redirect:/main";
    }






    @RequestMapping(value = "/main/{id}", method = RequestMethod.GET)
    public String rentForm(@PathVariable int id, Model model) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }
        Vehicle vehicle = this.vehicleService.getVehicleById(id);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("role", this.sessionObject.isLogged() ? this.sessionObject.getLoggedUser().getRole().toString() : null);
        return "rent";
    }

    @RequestMapping(value = "/main/{id}", method = RequestMethod.POST)
    public String rent(@ModelAttribute Vehicle vehicle) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.vehicleService.updateRent(vehicle);
        return "redirect:/main";
    }


    @RequestMapping(value = "/cheaty-do-zapisu-ordera", method = RequestMethod.GET)
    public String zapis() {
        List<Vehicle> vehicle = this.vehicleService.getAllVehicles();

        Order order = new Order();
        order.setStatus(Order.Status.RENTED);
        order.setUser(this.sessionObject.getLoggedUser());

        OrderPosition orderPosition1 = new OrderPosition();
        orderPosition1.setPlate(order.getPlate());
        orderPosition1.setVehicle(vehicle.get(0));
        orderPosition1.setOrder(order);

        order.getPositions().add(orderPosition1);

        this.orderService.saveOrder(order);
        return "redirect:/main";
    }

    @RequestMapping(value = "/cheaty-do-odczytania-ordera", method = RequestMethod.GET)
    public String odczyt() {
        Order order = this.orderService.getOrderById(1);

        System.out.println(order);

        return "redirect:/main";
    }
}
