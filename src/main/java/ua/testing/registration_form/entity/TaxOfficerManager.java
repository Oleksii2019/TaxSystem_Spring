package ua.testing.registration_form.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.DAO.TaxofficerRepository;
import ua.testing.registration_form.service.IOfficerLoginService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class TaxOfficerManager implements IOfficerLoginService {

    @Autowired
    TaxofficerRepository tor;

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        ArrayList<Taxofficer> taxof = new ArrayList<>();;
        taxof.addAll(tor.findAll());
        //List<Taxpayer> lst = tor.findByLoginEquals(login);
        boolean c = IntStream.range(0, taxof.size()).noneMatch(i ->
                login.equals(taxof.get(i).getLogin())
                        && password.equals(taxof.get(i).getPassword()));
        return c;
    }

    public Taxofficer getOneTaxofficer() {
        return tor.getOne(1L);
    }
}
