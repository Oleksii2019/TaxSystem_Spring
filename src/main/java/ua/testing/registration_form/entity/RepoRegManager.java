package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class RepoRegManager extends Users {

    public void saveUser(User user) throws RuntimeException {
        if (getUsers().isEmpty()) {
            ur.findAll().forEach(x -> getUsers().add(x));
        }

        if (checkExistUserInBase(user)) {
            try {
                ur.save(user); // В базу
            } catch(RuntimeException ex) {
                log.info("{}", user);
                throw new IllegalArgumentException("x_DB");
            }
            getUsers().add(user); // В масив
        } else {
            throw new IllegalArgumentException("x_reg");
        }
        toView(user);
    }

    private void toView(User user) {
        log.info("{}", user);
    }

//    public ArrayList<User> getUserToService() {
//        return getUsers();
//    }

}
