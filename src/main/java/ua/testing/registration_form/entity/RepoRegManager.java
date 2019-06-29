package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.registration_form.DAO.UserRepository;

import java.util.ArrayList;

@Slf4j
@Component
public class RepoRegManager extends Users implements UserRepository {

    @Override
    public void getUserFromSeervice(User user) {
        saveUser(user);
    }

    private void saveUser(User user) {
        if (checkUser(user)) {
            getUsers().add(user);
        }
        toView(user);
    }

    private void toView(User user) {
        log.info("{}", user);
    }

}
