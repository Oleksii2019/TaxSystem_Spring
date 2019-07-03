package ua.testing.registration_form.service;

import ua.testing.registration_form.dto.UserDTO;

public interface ILoginFromController {
    boolean checkLogin(UserDTO note);
}
