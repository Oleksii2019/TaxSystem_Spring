package ua.testing.registration_form.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.model.entity.RoleType;
import ua.testing.registration_form.dto.UserDTO;

@RestController
@RequestMapping(value = "/login_natural_person")
public class NPersonLoginController extends PersonLoginController {

    public void getLogin(UserDTO user) throws Exception {
        if (lc.checkLogin(user, true)) {
            throw new Exception();
        }
        loginToSecurity(user);
    }

    public void loginToSecurity(UserDTO user) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getLogin(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList
                        (RoleType.ROLE_PUSER.toString()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
