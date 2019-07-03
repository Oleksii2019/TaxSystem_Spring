package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.RepoRegManager;
import ua.testing.registration_form.entity.User;

@Service
public class RegFormService implements IRegFormControllerToService {

    private RepoRegManager rrm;

    @Autowired
    public RegFormService(RepoRegManager rrm) {
        this.rrm = rrm;
    }

    @Override
    public void fromRegFormController(NoteDTO note) {
        User user = new User();
        user.setName(note.getName());
        user.setLogin(note.getLogin());
        user.setPassword("1111");
        rrm.saveUser(user);
    }

}
