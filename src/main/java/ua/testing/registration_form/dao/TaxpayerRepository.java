package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.testing.registration_form.entity.Taxpayer;

import java.util.List;

/**
 *
 */
public interface TaxpayerRepository extends JpaRepository<Taxpayer, Long> {
    List<Taxpayer> findByLoginIs(String login);

    List<Taxpayer> findByLoginAndPasswordIs(String login, String password);

    @Query("select u from Taxpayer u where u.name IS NOT NULL")
    List<Taxpayer> findAllTaxpayers();

}
