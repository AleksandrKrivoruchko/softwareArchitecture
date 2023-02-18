package com.avk.controller;

import com.avk.entity.User;
import com.avk.service.UserService;
import com.avk.service.impl.UserServiceImp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {
    UserService userService = new UserServiceImp();
    @RequestMapping("/")
    String main() {
        return "Hello from Controller";
    }

    @RequestMapping("/loginForm")
    ModelAndView loginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginForm.html");
        return modelAndView;
    }

    @RequestMapping("/registrationForm")
    ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationForm.html");
        return modelAndView;
    }

    @PostMapping("/login")
    String login(@RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        return userService.login(user);
    }

    @PostMapping("/registration")
    String registration(@RequestParam String name, @RequestParam String surname,
                        @RequestParam String login, @RequestParam String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        return userService.registration(user);
    }
}
