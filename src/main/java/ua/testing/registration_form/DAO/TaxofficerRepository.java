package ua.testing.registration_form.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.Taxofficer;

public interface TaxofficerRepository extends JpaRepository<Taxofficer, Long> {

}
