package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.ReportDTO;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
//@RequestMapping(value = "/")
public class ReportController {

    @Autowired
    IReportController rc;

    @RequestMapping(value = "/not_format/reports_payer", method = RequestMethod.GET)
    public List<ReportDTO> loadTaxpayerReports() throws Exception {
        return rc.getTaxpayerReportDTO(loginFromSecurity());
    }

    @RequestMapping(value = "/not_format/reports_officer", method = RequestMethod.GET)
    public List<ReportDTO> loadOfficerReports() throws Exception {
        return rc.getOfficerReportDTO(loginFromSecurity());
    }

    @RequestMapping(value = "/payer_report_list", method = RequestMethod.POST)
    public String getReportForPayerFromForm(String report) throws Exception {
        ReportDTO reportDTO = rc.getTaxpayerReportDTOByLoginAndTime(report);
        reportDTO.setAssessed(false);
        reportDTO.setNote("");
        rc.updateReport(reportDTO);
        return "Done";
    }

    @RequestMapping(value = "/payer_report_list/creation", method = RequestMethod.POST)
    public String getCreateReportFromForm() throws Exception {
        String loginName = loginFromSecurity();
        log.info("{}", loginName);
        ReportDTO reportDTO = createNewReport(loginName);
        rc.saveNewReport(reportDTO);
        return reportDTO.toString();
    }

    @RequestMapping(value = "/officer_report_list", method = RequestMethod.POST)
    public String getReportForOfficerFromForm(String report,
                                              String accBtn,
                                              String reclText,
                                              String reclBtn) throws Exception {
        ReportDTO reportDTO = rc.getTaxpayerReportDTOByLoginAndTime(report);
        reportDTO.setAssessed(true);
        reportDTO.setTaxofficerLogin(loginFromSecurity());
        if (accBtn != null) {
            reportDTO.setAccepted(true);
            reportDTO.setAcceptTime(LocalDateTime.now());
        } else {
            if (reclBtn != null) {
                reportDTO.setAccepted(false);
                reportDTO.setNote(reclText);
            }
        }
        rc.updateReport(reportDTO);
        return reportDTO.toString();
    }

    private String loginFromSecurity() throws Exception {
        return SecurityContextHolder.getContext()
               .getAuthentication().getPrincipal().toString();
    }


    public ReportDTO createNewReport(String loginName) {
        return  ReportDTO.builder()
                .taxpayerLogin(loginName)
                .creationTime(LocalDateTime.now())
                .accepted(false)
                .assessed(false)
                .build();
    }


    @RequestMapping(value = "/login/username")
    public String loginNamePage() throws Exception {
        return loginFromSecurity();
    }

}
