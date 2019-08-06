package ua.testing.registration_form.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.controller.ILoginController;
import ua.testing.registration_form.dto.UserDTO;

@Service
public class LoginService implements ILoginController {

    @Autowired
    IPayerLoginService ps;

    @Autowired
    IOfficerLoginService os;

    @Override
    public boolean checkLogin(UserDTO user,  boolean isPayer) {
        if(isPayer)
            return ps.checkLogin(user.getLogin(), user.getPassword());
        else
            return os.checkLogin(user.getLogin(), user.getPassword());
    }

}
