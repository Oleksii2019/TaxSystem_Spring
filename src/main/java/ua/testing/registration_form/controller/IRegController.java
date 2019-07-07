package ua.testing.registration_form.controller;

import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.Taxpayer;

public interface IRegController {
    void fromRegForm(NoteDTO note);
    Iterable<Taxpayer> getAllTaxpayers();
}
