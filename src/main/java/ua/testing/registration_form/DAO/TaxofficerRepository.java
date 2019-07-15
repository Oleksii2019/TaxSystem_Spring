package ua.testing.registration_form.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.Taxofficer;
import ua.testing.registration_form.entity.Taxpayer;

import java.util.List;

public interface TaxofficerRepository extends JpaRepository<Taxofficer, Long> {
    List<Taxpayer> findByLoginEquals(String login);

}
