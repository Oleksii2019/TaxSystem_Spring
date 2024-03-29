package ua.testing.registration_form.controller;

import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.entity.Taxpayer;
import ua.testing.registration_form.dto.ReplacementDTO;

import java.util.List;

public interface IReportController {
    void saveNewReport(ReportDTO reportDTO);

    List<ReportDTO> getTaxpayerReportDTO(String taxpayerlogin);

    List<ReportDTO> getOfficerReportDTO(String officerlogin);

    ReportDTO getTaxpayerReportDTOByLoginAndTime(String loginAndTime);

    void updateReport(ReportDTO reportDTO);

    void acceptReport(ReportDTO reportDTO);
    
    void assessReport(ReportDTO reportDTO);

    Taxofficer getTaxofficerForTaxpayerLogin(String login);

    Taxpayer getTaxpayerByLogin(String login);

    void saveNewReplacementRequest(ReplacementDTO replacementDTO);

}

