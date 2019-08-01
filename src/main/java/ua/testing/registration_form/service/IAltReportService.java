package ua.testing.registration_form.service;

import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.entity.Report;
import ua.testing.registration_form.entity.ReportAlteration;

import java.util.List;

public interface IAltReportService {
    List<ReportAlteration> getAltReportsForReport(Report report);
    ReportAlteration getAltNotAcceptedReportForReport(Report report);
    void deleteAltReports(List<ReportAlteration> altReports);
    void saveAltReports(List<ReportAlteration> altReports);
    void saveNewAltReport(AltReportDTO altReportDTO);
    void setAltRaportAsAccepted(ReportAlteration altReport);

//    void updateAltReports(Report report);

}
