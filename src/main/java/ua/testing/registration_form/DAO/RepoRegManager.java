package ua.testing.registration_form.DAO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.User;
import ua.testing.registration_form.entity.Users;

@Slf4j
@Component
public class RepoRegManager extends Users {

    public void dtoToUser(NoteDTO note) {
        User user = new User();
        user.setNames(note.getNames());
        user.setLogin(note.getLogin());
        if (checkUser(user)) {
            getUsers().add(user);
        }
        toView(user);
    }

    private void toView(User user) {
        log.info("{}", user);
    }

}
