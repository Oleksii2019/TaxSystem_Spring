package ua.testing.registration_form.model.dao.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.model.dao.AltReportRepository;
import ua.testing.registration_form.model.entity.Report;
import ua.testing.registration_form.model.entity.ReportAlteration;
import ua.testing.registration_form.model.service.IAltReportService;

import java.time.LocalDateTime;

@Slf4j
@Repository
public class AltReportManager implements IAltReportService {

    @Autowired
    AltReportRepository arr;

    @Override
    public void setAltReportAsAccepted(ReportAlteration altReport) {
        arr.setAltReportAsAccepted(altReport.getId(), LocalDateTime.now());
    }

    @Override
    public ReportAlteration getAltNotAcceptedReportForReport(Report report) {
        return arr.findNotAcceptedByReport(report);
    }

    @Override
    public void saveNewAltReport(AltReportDTO altReportDTO) {
        arr.save(ReportAlteration.builder()
                .creationTime(altReportDTO.getCreationTime())
                .note(altReportDTO.getNote())
                .report(altReportDTO.getReport())
                .build()
        );
    }

}
