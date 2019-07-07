package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.controller.IRegController;
import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.Taxpayer;

@Service
public class RegService implements IRegController {

    @Autowired
    IRegService rs;

    @Override
    public void fromRegForm(NoteDTO note) throws RuntimeException {
        rs.saveNewTaxpayer(note.getName(), note.getLogin(), note.getPassword());
    }

    @Override
    public Iterable<Taxpayer> getAllTaxpayers() {
        return rs.getAllTaxpayers();
    }
}
