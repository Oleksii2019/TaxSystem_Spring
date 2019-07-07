package ua.testing.registration_form.service;

import ua.testing.registration_form.entity.Taxpayer;

public interface IRegService {
    void saveNewTaxpayer(String name, String login, String password);
    Iterable<Taxpayer> getAllTaxpayers();
}
