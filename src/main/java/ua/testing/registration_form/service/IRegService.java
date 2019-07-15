package ua.testing.registration_form.service;

import ua.testing.registration_form.dto.NoteDTO;
import ua.testing.registration_form.entity.Taxpayer;

import java.util.List;

public interface IRegService {
    void saveNewTaxpayer(String name, String login, String password);
    List<Taxpayer> getAllTaxpayers();
}
