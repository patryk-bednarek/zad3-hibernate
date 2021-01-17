package pl.edu.wszib.car.rent.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.car.rent.model.User;
import pl.edu.wszib.car.rent.model.Vehicle;
import pl.edu.wszib.car.rent.services.IRentService;
import pl.edu.wszib.car.rent.services.IVehicleService;
import pl.edu.wszib.car.rent.session.SessionObject;

import javax.annotation.Resource;


@Controller
public class RentController {
    @Autowired
    IRentService rentService;

    @Resource
    SessionObject sessionObject;


    @RequestMapping(value = "/rent", method = RequestMethod.GET)
    public String rent(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("vehicles", this.sessionObject.getRent());
        model.addAttribute("isLogged", this.sessionObject.isLogged());

        return "rent";
    }

    @RequestMapping(value = "/rent/{id}", method = RequestMethod.GET)
    public String rent(@PathVariable int id) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }
        this.rentService.addVehicleByIdToRent(id);


        return "redirect:/main";
    }



    @RequestMapping(value = "/rent/{id}", method = RequestMethod.POST)
    public String rent(@ModelAttribute Vehicle vehicle) {
        if(!this.sessionObject.isLogged() || this.sessionObject.getLoggedUser().getRole() != User.Role.ADMIN) {
            return "redirect:/login";
        }

        this.rentService.updateRent(vehicle);
        return "redirect:/main";
    }
}
