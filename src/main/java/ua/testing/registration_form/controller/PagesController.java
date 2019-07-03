package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.DAO.UserRepository;
import ua.testing.registration_form.entity.User;

@Slf4j
@Controller
public class PagesController {

    @Autowired
    private UserRepository db;

    @RequestMapping(value = "/")
    public String loginPage() {
        return "index.html";
    }

    @RequestMapping(value = "not_format/users", method = RequestMethod.GET)
    public @ResponseBody Iterable<User> getAllUsers() {
        return db.findAll();
    }

    @RequestMapping("/form")
    public String regForm(){
        return "reg_form";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userPage(){
        return "users/index.html";
    }

}
