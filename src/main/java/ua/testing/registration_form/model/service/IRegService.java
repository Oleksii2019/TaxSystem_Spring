package ua.testing.registration_form.model.service;

import ua.testing.registration_form.model.entity.Taxpayer;

import java.util.List;

public interface IRegService {
    void saveNewTaxpayer(String name, String login, String password);
    List<Taxpayer> getAllTaxpayers();
}
