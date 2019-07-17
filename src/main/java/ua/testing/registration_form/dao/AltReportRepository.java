package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.testing.registration_form.entity.Report;
import ua.testing.registration_form.entity.ReportAlteration;

import java.util.List;

public interface AltReportRepository extends JpaRepository<ReportAlteration, Long> {

    List<ReportAlteration> findAllByReport(Report report);

}
