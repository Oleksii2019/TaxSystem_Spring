package ua.testing.registration_form.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.testing.registration_form.model.entity.Report;
import ua.testing.registration_form.model.entity.ReportAlteration;

import java.time.LocalDateTime;
import java.util.List;

public interface AltReportRepository
        extends JpaRepository<ReportAlteration, Long> {

    List<ReportAlteration> findAllByReport(Report report);

    @Query("select u from ReportAlteration u where u.accepted = false "
            + "AND u.report = :report")
    ReportAlteration findNotAcceptedByReport(@Param("report") Report report);

    @Modifying
    @Query("update ReportAlteration u set u.accepted = true, "
            + "u.acceptTime = :acceptTime where u.id = :altReportId")
    void setAltReportAsAccepted(@Param("altReportId") Long altReportId,
                                @Param("acceptTime") LocalDateTime acceptTime);
}
