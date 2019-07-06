package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.dto.UserDTO;
import ua.testing.registration_form.entity.RepoRegManager;

@Service
public class LoginFormService implements ILoginFromController {

    private final RepoRegManager rrm;

    @Autowired
    public LoginFormService(RepoRegManager rrm) {
        this.rrm = rrm;
    }

    @Override
    public boolean checkLogin(UserDTO user) {
        return rrm.checkUserByLogin(user.getLogin());
    }

}
