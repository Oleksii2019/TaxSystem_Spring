package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.dao.TaxpayerRepository;
import ua.testing.registration_form.service.IPayerLoginService;
import ua.testing.registration_form.service.IRegService;

import java.util.List;

@Slf4j
@Repository
public class TaxpayerManager implements IPayerLoginService, IRegService {
//    private ArrayList<Taxpayer> taxpayers;

    @Autowired
    TaxpayerRepository tr;

    @Autowired
    TaxOfficerManager tom;

//    public TaxpayerManager() {
//        taxpayers = new ArrayList<>();
//    }

//    private void getTaxpayersFromDB() throws RuntimeException {
//        taxpayers.clear();
//        try {
//            taxpayers.addAll(tr.findAll());
//        } catch(RuntimeException ex) {
//            throw new ArrayStoreException("x_getDB");
//        }
//    }

    private boolean checkExistTaxpayerInBase(String login)
            throws RuntimeException {
//        getTaxpayersFromDB();
        return tr.findByLoginIs(login).isEmpty();
//                IntStream.range(0, taxpayers.size()).noneMatch(i ->
//                login.equals(taxpayers.get(i).getLogin()));
    }

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
//        getTaxpayersFromDB();

//        List<Taxpayer> matchLogin = tr.findByLoginAndPasswordIs(login, password);

//        List<Taxpayer> matchLogin = tr.findByLoginIs(login);
        return tr.findByLoginAndPasswordIs(login, password).isEmpty();
//                IntStream.range(0, taxpayers.size()).noneMatch(i ->
//                login.equals(taxpayers.get(i).getLogin())
//                && password.equals(taxpayers.get(i).getPassword()));
    }

    public Taxpayer getTaxpayerByLogin(String login) {
        List<Taxpayer> tt = tr.findByLoginIs(login);
        if (!tt.isEmpty()) {
            return tt.get(0);
        } else {
            return tr.findByLoginIs("Nike2").get(0);
        }
    }

    public Taxofficer getTaxofficerForTaxpayerLogin(String login) {
        Taxpayer tt = getTaxpayerByLogin(login);
        return tt.getTaxofficer();
    }

    @Override
    public void saveNewTaxpayer(String name, String login, String password)
            throws RuntimeException {
        if (checkExistTaxpayerInBase(login)) {
            Taxpayer taxpayer = Taxpayer.builder()
                    .name(name)
                    .login(login)
                    .password(password)
                    .role(RoleType.ROLE_PUSER)
                    .taxofficer(tom.getOneTaxofficer())
                    .build();
            try {
                tr.save(taxpayer);
            } catch(RuntimeException ex) {
                log.info("{}", taxpayer);
                throw new IllegalArgumentException("x_DB");
            }
        } else {
            throw new IllegalArgumentException("x_reg");
        }
    }

    @Override
    public List<Taxpayer> getAllTaxpayers() {
        return tr.findAllTaxpayers();
    }
}
