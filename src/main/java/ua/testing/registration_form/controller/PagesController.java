package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class PagesController {

    @RequestMapping(value = "/")
    public String loginPage() {
        return "index.html";
    }

    @RequestMapping("/reg_form")
    public String regPage(){
        return "reg_form";
    }

    @RequestMapping(value = "/users")
    public String usersPage(){
        return "users/usersTab.html";
    }

}
