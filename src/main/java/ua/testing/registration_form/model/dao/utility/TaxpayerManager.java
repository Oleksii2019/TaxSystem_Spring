package ua.testing.registration_form.model.dao.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.model.entity.RoleType;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.service.IPayerLoginService;
import ua.testing.registration_form.model.dao.TaxpayerRepository;
import ua.testing.registration_form.model.entity.Taxpayer;
import ua.testing.registration_form.model.service.IRegService;

import java.sql.SQLException;
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
        return tr.findByLoginIs(login).isEmpty();
    }

    @Override
    public boolean checkLogin(String login, String password)
            throws RuntimeException {
        return tr.findByLoginAndPasswordIs(login, password).isEmpty();
    }

    public Taxpayer getTaxpayerByLogin(String login) {
        List<Taxpayer> tt = tr.findByLoginIs(login);
        if (!tt.isEmpty()) {
            return tt.get(0);
        } else {
            throw new IllegalArgumentException("x_DB");
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
