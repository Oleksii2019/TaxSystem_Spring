package ua.testing.registration_form.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.entity.Taxpayer;

import java.util.List;

public interface TaxofficerRepository
        extends JpaRepository<Taxofficer, Long> {
//    List<Taxpayer> findByLoginEquals(String login);
}
