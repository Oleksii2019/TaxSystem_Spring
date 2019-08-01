package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.testing.registration_form.entity.Report;
import ua.testing.registration_form.entity.ReportAlteration;

import java.util.List;

public interface AltReportRepository extends JpaRepository<ReportAlteration, Long> {

    List<ReportAlteration> findAllByReport(Report report);

    @Query("select u from ReportAlteration u where u.accepted = false "
            + "AND u.report = :report")
    ReportAlteration findNotAcceptedByReport(@Param("report") Report report);

    @Modifying
    @Query("update ReportAlteration u set u.accepted = true "
            + "where u.id = :altReportId")
    void setAltRaportAsAccepted(@Param("altReportId") Long altReportId);
}
