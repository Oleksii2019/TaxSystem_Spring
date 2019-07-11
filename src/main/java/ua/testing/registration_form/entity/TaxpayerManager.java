package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.DAO.TaxofficerRepository;
import ua.testing.registration_form.DAO.TaxpayerRepository;
import ua.testing.registration_form.service.ILoginService;
import ua.testing.registration_form.service.IRegService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class TaxpayerManager implements ILoginService, IRegService {
    private ArrayList<Taxpayer> taxpayers;

    @Autowired
    TaxpayerRepository tr;

    @Autowired
    TaxofficerRepository tor;

    public TaxpayerManager() {
        taxpayers = new ArrayList<>();
    }

    private void getTaxpayersFromDB() throws RuntimeException {
        taxpayers.clear();
        try {
            taxpayers.addAll(tr.findAll());
        } catch(RuntimeException ex) {
            throw new ArrayStoreException("x_getDB");
        }
    }

    private boolean checkExistTaxpayerInBase(String login)
            throws RuntimeException {
        getTaxpayersFromDB();
        return IntStream.range(0, taxpayers.size()).noneMatch(i ->
                login.equals(taxpayers.get(i).getLogin()));
    }

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        getTaxpayersFromDB();
        List<Taxpayer> l = tr.findByLoginEquals(login);

//        boolean b = tr.findByLoginAndPasswordEquals(login, password);
        //boolean b = tr.findByLoginFalse(login);
        return IntStream.range(0, taxpayers.size()).noneMatch(i ->
                login.equals(taxpayers.get(i).getLogin())
                && password.equals(taxpayers.get(i).getPassword()));
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
                    .taxofficer(tor.getOne(1L))
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
    public Iterable<Taxpayer> getAllTaxpayers() {
        return tr.findAll();
    }
}
