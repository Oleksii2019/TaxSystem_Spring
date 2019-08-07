package ua.testing.registration_form.model.dao.utility;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.service.IOfficerLoginService;
import ua.testing.registration_form.model.dao.TaxofficerRepository;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class TaxOfficerManager implements IOfficerLoginService {

    @Autowired
    TaxofficerRepository tor;

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        ArrayList<Taxofficer> taxof = new ArrayList<>();
        try {
            taxof.addAll(tor.findAll());
        } catch(JDBCException e) {
            log.error(e.getCause().toString());
            throw new RuntimeException();
        }
        return IntStream.range(0, taxof.size()).noneMatch(i ->
                login.equals(taxof.get(i).getLogin())
                        && password.equals(taxof.get(i).getPassword()));
    }

    public Taxofficer getOneTaxofficer() {
        try {
            return tor.getOne(1L);
        } catch(JDBCException e) {
            log.error(e.getCause().toString());
            throw new RuntimeException();
        }
    }
}
