package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.DAO.UserRepository;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.User;

@Service
public class RegFormService implements IRegFormControllerToService {

   @Autowired
    UserRepository ur;

    @Override
    public void fromRegFormController(NoteDTO note) {
        User user = new User();
        user.setNames(note.getNames());
        user.setLogin(note.getLogin());
        ur.getUserFromSeervice(user);
    }

}
