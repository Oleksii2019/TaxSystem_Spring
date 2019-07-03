package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.UserDTO;
import ua.testing.registration_form.service.LoginFormService;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginFormController {

    private final LoginFormService loginFormService;

    @Autowired
    public LoginFormController(LoginFormService loginFormService) {
        this.loginFormService = loginFormService;
    }

    /**
     * Метод НЕ реализован
     */
    //@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void getLogin(UserDTO user){
        log.info("{}", user);
    }

}
