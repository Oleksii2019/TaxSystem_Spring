package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class PagesController {

    @RequestMapping(value = "/")
    public String sssPage() {
        return "index";
    }

    @RequestMapping(value = "/login_natural_person")
    public String naturalPersonLoginPage() {
        return "nperson_log_form";
    }

    @RequestMapping(value = "/login_juridical_person")
    public String juridicalPersonLoginPage() {
        return "jperson_log_form";
    }

    @RequestMapping(value = "/login_taxofficer")
    public String taxOfficerLoginPage() {
        return "officer_log_form";
    }

    @RequestMapping("/reg_form")
    public String regPage(){
        return "reg_form";
    }

    @RequestMapping(value = "/users")
    public String usersPage(){
        return "usersTab";
    }

}
