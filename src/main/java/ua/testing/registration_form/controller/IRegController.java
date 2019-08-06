package ua.testing.registration_form.controller;

import ua.testing.registration_form.dto.NoteDTO;

import java.util.List;

public interface IRegController {
    void fromRegForm(NoteDTO note);
    List<NoteDTO> getAllTaxpayers();
}
