package ua.testing.registration_form.controller;

import ua.testing.registration_form.dto.ReportDTO;

import java.util.List;

public interface IReportController {
    void saveNewReport(ReportDTO reportDTO);

    List<ReportDTO> getTaxpayerReportDTO(String taxpayerlogin);

    List<ReportDTO> getOfficerReportDTO(String officerlogin);

    ReportDTO getTaxpayerReportDTOByLoginAndTime(String loginAndTime);

    void updateReport(ReportDTO reportDTO);

}

