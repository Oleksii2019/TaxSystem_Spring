package ua.testing.registration_form.model.dao.utility;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.model.entity.RoleType;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.service.IPayerLoginService;
import ua.testing.registration_form.model.dao.TaxpayerRepository;
import ua.testing.registration_form.model.entity.Taxpayer;
import ua.testing.registration_form.model.service.IRegService;

import java.util.List;

@Slf4j
@Repository
public class TaxpayerManager implements IPayerLoginService, IRegService {

    @Autowired
    TaxpayerRepository tr;

    @Autowired
    TaxOfficerManager tom;

    private boolean checkExistTaxpayerInBase(String login)
            throws RuntimeException {
        try {
            return tr.findByLoginIs(login).isEmpty();
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
        }
        return false;
    }

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        try {
            return tr.findByLoginAndPasswordIs(login, password).isEmpty();
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
        }
        return false;
    }

    public Taxpayer getTaxpayerByLogin(String login) {
        List<Taxpayer> tt = tr.findByLoginIs(login);
        if (tt.isEmpty()) {
            throw new IllegalArgumentException("x_DB");
        } else {
            return tt.get(0);
        }
    }

    public Taxofficer getTaxofficerForTaxpayerLogin(String login) {
        Taxpayer tt = getTaxpayerByLogin(login);
        if (tt == null) {
            throw new IllegalArgumentException("x_DB");
        } else {
            return tt.getTaxofficer();
        }
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
            } catch(JDBCException e) {
                log.error(e.getCause().toString());
            }
        } else {
            throw new IllegalArgumentException("x_reg");
        }
    }

    @Override
    public List<Taxpayer> getAllTaxpayers() {
        try {
            return tr.findAllTaxpayers();
        } catch(JDBCException e) {
            log.error(e.getCause().toString());
            throw new RuntimeException();
        }
    }
}
