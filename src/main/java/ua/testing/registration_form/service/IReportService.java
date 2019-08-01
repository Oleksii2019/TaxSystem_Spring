package ua.testing.registration_form.service;

import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.entity.Report;
import ua.testing.registration_form.entity.Taxofficer;
import ua.testing.registration_form.entity.Taxpayer;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportService {
    void saveNewReport(ReportDTO reportDTO);

    List<Report> getTaxpayerReports(String taxpayerlogin);

    List<Report> getOfficerReports(String officerLogin);

    List<Report> getTaxpayerReportByLoginAndTime(String taxpayerLogin,
                                                 LocalDateTime dateTime);

    Report get_1_TaxpayerReportByLoginAndTime(String taxpayerLogin,
                                              LocalDateTime dateTime);

    void setReportAsAssessed(Report report);

    void setReportAsNotAssessed(Report report);

    void setReportAsAccepted(Report report);

    void deleteReport(ReportDTO reportDTO);

    Taxofficer getTaxofficerForTaxpayerLogin(String login);

    Taxpayer getTaxpayerByLogin(String login);
}
