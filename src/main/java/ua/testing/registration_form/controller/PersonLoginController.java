package ua.testing.registration_form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.testing.registration_form.dto.UserDTO;

public abstract class PersonLoginController {

    @Autowired
    ILoginController lc;

    @RequestMapping(method = RequestMethod.POST)
    public abstract void getLogin(UserDTO user) throws Exception;

    public abstract void loginToSecurity(UserDTO user) throws Exception;

}
