package ua.testing.registration_form.model.service;

import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.model.entity.Report;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.entity.Taxpayer;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportService {
    void saveNewReport(ReportDTO reportDTO);

    List<Report> getTaxpayerReports(String taxpayerlogin);

    List<Report> getOfficerReports(String officerLogin);

    Report getTaxpayerReportByLoginAndTime(String taxpayerLogin,
                                              LocalDateTime dateTime);

    void setReportAsAssessed(Report report);

    void setReportAsNotAssessed(Report report);

    void setReportAsAccepted(Report report);

    Taxofficer getTaxofficerForTaxpayerLogin(String login);

    Taxpayer getTaxpayerByLogin(String login);
}
