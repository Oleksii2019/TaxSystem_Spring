package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.UserDTO;

@Slf4j
@RestController
@RequestMapping(value = "/login")
public class LoginFormController {

    @Autowired
    ILoginController lc;


    @RequestMapping(method = RequestMethod.POST)
    public void getLogin(UserDTO user) throws Exception {
        log.info("{}", user);
        if (lc.checkLogin(user)) {
            throw new Exception();
        }
    }

}
