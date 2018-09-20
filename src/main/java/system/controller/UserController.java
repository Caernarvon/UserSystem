package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public ModelAndView validateUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFromServer", new User());
        modelAndView.setViewName("users_check_page");
        return modelAndView;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody String checkUser(@ModelAttribute("userFromServer") User user){
        try {
            return userService.check(user);
        }
        catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registerUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newUser", new User());
        modelAndView.setViewName("users_register_page");
        return modelAndView;
    }

    @RequestMapping(value = "/registerNewUser", method = RequestMethod.POST)
    public @ResponseBody String registerUser(@ModelAttribute("newUser") User user) {
        try{
            userService.addUser(user);
            return "successful";
        }
        catch (Exception e) {
            return e.toString();
        }
    }
}
