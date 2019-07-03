package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class RepoRegManager extends Users {

    public void getUserFromService(User user) {
        saveUser(user);
    }

    public void saveUser(User user) {
        if (getUsers().isEmpty()) {
            ur.findAll().forEach(x -> getUsers().add(x));
        }

        if (checkUser(user)) {
            ur.save(user); // В базу
            getUsers().add(user); // В масив
        }
        toView(user);
    }

    private void toView(User user) {
        log.info("{}", user);
    }

    public ArrayList<User> getUserToService() {
        return getUsers();
    }

}
