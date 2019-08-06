package ua.testing.registration_form.model.service;

import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.model.entity.Report;
import ua.testing.registration_form.model.entity.ReportAlteration;

import java.util.List;

public interface IAltReportService {
    ReportAlteration getAltNotAcceptedReportForReport(Report report);
    void saveNewAltReport(AltReportDTO altReportDTO);
    void setAltReportAsAccepted(ReportAlteration altReport);
}
