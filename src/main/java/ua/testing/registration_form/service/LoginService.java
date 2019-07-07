package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.controller.ILoginController;
import ua.testing.registration_form.dto.UserDTO;

@Service
public class LoginService implements ILoginController {

    @Autowired
    ILoginService ls;

    @Override
    public boolean checkLogin(UserDTO user) {
        return ls.checkLogin(user.getLogin(), user.getPassword());
    }

}
