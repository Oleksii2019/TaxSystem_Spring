package ua.testing.registration_form.model.dao.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.service.IOfficerLoginService;
import ua.testing.registration_form.model.dao.TaxofficerRepository;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Repository
public class TaxOfficerManager implements IOfficerLoginService {

    @Autowired
    TaxofficerRepository tor;

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        ArrayList<Taxofficer> taxof = new ArrayList<>();
        taxof.addAll(tor.findAll());
        return IntStream.range(0, taxof.size()).noneMatch(i ->
                login.equals(taxof.get(i).getLogin())
                        && password.equals(taxof.get(i).getPassword()));
    }

    public Taxofficer getOneTaxofficer() {
        return tor.getOne(1L);
    }
}
