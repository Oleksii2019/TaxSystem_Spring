package ua.testing.registration_form.controller;

import ua.testing.registration_form.dto.UserDTO;

public interface ILoginController {
    boolean checkLogin(UserDTO user);
}
