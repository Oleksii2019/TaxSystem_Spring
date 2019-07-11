package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.UserDTO;
import ua.testing.registration_form.entity.RoleType;

@Slf4j
@RestController
@RequestMapping(value = "/login_juridical_person")
public class JPersonLoginController {

    @Autowired
    ILoginController lc;

    @RequestMapping(method = RequestMethod.POST)
    public void getLogin(UserDTO user) throws Exception {
        log.info("{}", user);
        if (lc.checkLogin(user)) {
            throw new Exception();
        }
        loginToSecurity(user);
    }

    private void loginToSecurity(UserDTO user) throws Exception {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getLogin(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList
                        (RoleType.ROLE_PUSER.toString()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
