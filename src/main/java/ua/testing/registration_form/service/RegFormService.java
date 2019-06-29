package ua.testing.registration_form.service;

import org.springframework.stereotype.Service;
import ua.testing.registration_form.DAO.RepoRegManager;
import ua.testing.registration_form.dto.NoteDTO;

@Service
public class RegFormService {
    private RepoRegManager r;

    public RegFormService(RepoRegManager r) {
        this.r = r;
    }

    public void inputNote(NoteDTO name) {
        r.dtoToUser(name);
    }

}
