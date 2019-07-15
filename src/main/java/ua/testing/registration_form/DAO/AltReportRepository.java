package ua.testing.registration_form.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.ReportAlteration;

public interface AltReportRepository extends JpaRepository<ReportAlteration, Long> {

}
