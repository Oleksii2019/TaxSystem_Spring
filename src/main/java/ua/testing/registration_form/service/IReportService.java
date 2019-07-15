package ua.testing.registration_form.service;

import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.entity.Report;

import java.time.LocalDateTime;
import java.util.List;

public interface IReportService {
    void saveNewReport(ReportDTO reportDTO);
    List<Report> getTaxpayerReports(String taxpayerlogin);
    List<Report> getOfficerReports(String officerLogin);
    List<Report> getTaxpayerReportByLoginAndTime(String taxpayerLogin,
                                                 LocalDateTime dateTime);
    void deleteReport(ReportDTO reportDTO);

}
