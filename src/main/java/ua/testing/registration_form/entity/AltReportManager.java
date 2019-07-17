package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.dao.AltReportRepository;
import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.service.IAltReportService;

import java.util.List;

@Slf4j
@Repository
public class AltReportManager implements IAltReportService {

    @Autowired
    AltReportRepository arr;

    @Override
    public List<ReportAlteration> getAltReportsForReport(Report report) {
        return arr.findAllByReport(report);
    }

    @Override
    public void deleteAltReports(List<ReportAlteration> altReports) {
        altReports.stream().forEach(arr :: delete);
    }

    @Override
    public void saveAltReports(List<ReportAlteration> altReports) {
        altReports.stream().forEach(arr :: save);
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
