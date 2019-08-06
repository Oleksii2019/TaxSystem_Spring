package ua.testing.registration_form.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.model.entity.ReplacementRequest;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.entity.Taxpayer;

import java.util.List;

public interface ReplacementRequestRepository
        extends JpaRepository<ReplacementRequest, Long> {
    List<ReplacementRequest> findByTaxofficerAndTaxpayer(Taxofficer taxofficer,
                                                         Taxpayer taxpayer);
}
