package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.DAO.TaxpayerRepository;
import ua.testing.registration_form.service.ILoginService;
import ua.testing.registration_form.service.IRegService;

import java.util.ArrayList;

@Slf4j
@Repository
public class TaxpayerManager implements ILoginService, IRegService {
    private ArrayList<Taxpayer> taxpayers;

    @Autowired
    TaxpayerRepository tr;

    public TaxpayerManager() {
        taxpayers = new ArrayList<>();
    }

    private void getTaxpayersFromDB() throws RuntimeException {
        taxpayers.clear();
        try {
            tr.findAll().forEach(x -> taxpayers.add(x));
        } catch(RuntimeException ex) {
            throw new ArrayStoreException("x_getDB");
        }
    }

    private boolean checkExistTaxpayerInBase(String login)
            throws RuntimeException {
        getTaxpayersFromDB();
        for (int i = 0; i < taxpayers.size(); i++) {
            if (login.equals(taxpayers.get(i).getLogin())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        getTaxpayersFromDB();
        for (int i = 0; i < taxpayers.size(); i++) {
            if (login.equals(taxpayers.get(i).getLogin())
                    && password.equals(taxpayers.get(i).getPassword())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void saveNewTaxpayer(String name, String login, String password)
            throws RuntimeException {
        if (checkExistTaxpayerInBase(login)) {
            Taxpayer taxpayer = Taxpayer.builder()
                    .name(name)
                    .login(login)
                    .password(password)
                    .role(RoleType.ROLE_USER)
                    .build();
            try {
                tr.save(taxpayer); // В базу
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
