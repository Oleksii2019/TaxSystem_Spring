package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.RepoRegManager;
import ua.testing.registration_form.entity.User;

@Service
public class RegFormService implements IRegFormControllerToService {

    @Autowired
    RepoRegManager rrm;

    @Override
    public void fromRegFormController(NoteDTO note) {
        User user = new User();
        user.setName(note.getNames());
        user.setLogin(note.getLogin());
        user.setPassword("1111");
        rrm.saveUser(user);
    }

}
