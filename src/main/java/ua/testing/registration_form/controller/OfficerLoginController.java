package ua.testing.registration_form.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.UserDTO;
import ua.testing.registration_form.entity.RoleType;

@RestController
@RequestMapping(value = "/login_taxofficer")
public class OfficerLoginController  extends PersonLoginController  {

    public void getLogin(UserDTO user) throws Exception {
        if (lc.checkLogin(user, false)) {
            throw new Exception();
        }
        loginToSecurity(user);
    }

    public void loginToSecurity(UserDTO user) throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getLogin(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList
                        (RoleType.ROLE_OUSER.toString()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
