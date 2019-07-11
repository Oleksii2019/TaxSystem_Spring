package ua.testing.registration_form.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.Taxpayer;

import java.util.List;

/**
 *
 */
public interface TaxpayerRepository extends JpaRepository<Taxpayer, Long> {
    List<Taxpayer> findByLoginEquals(String login);

}
