package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.ReplacementDTO;
import ua.testing.registration_form.dto.ReportDTO;

import java.time.LocalDateTime;

/**
 *  Controller. It responds to the request from the client,
 *  finds out the control data from "/payer_report_list/creation" and
 *  "/officer_report_list/create" URLs and resolves the processing.
 *  In according with these data necessary method is called for
 *  payer's report or complaint creating, payer's report editing,
 *  officer's report creating.
 *  * @version v.1.0 19 Jun 2019
 *  * @author Oleksii Muratov
 */
@Slf4j
@Controller
public class ReportController {

    @Autowired
    IReportController rc;

    /**
     * HTTP request handler method of MVC for payer's requests resolving
     * @param createBtn the solution for report creating,
     * @param complaintBtn the solution for complaint creating,
     * @param editBtn the solution for report editing,
     * @param report the data for report identification
     */
    @RequestMapping(value = "/payer_report_list/creation",
                    method = RequestMethod.POST)
    public String modifyReportByPayer(String createBtn,
                                      String complaintBtn,
                                      String editBtn,
                                      String report) throws Exception {
        if (createBtn != null) {
            createReportByPayer();
        } else if (editBtn != null) {
            editReportByPayer(report);
        } else if (complaintBtn != null) {
            complaintReportByPayer();
        }
        return "redirect:/payer_report_list";
    }

    /**
     * Provides payer's report creating
     */
    private void createReportByPayer() throws Exception {
        ReportDTO reportDTO = createNewReport(loginFromSecurity());
        rc.saveNewReport(reportDTO);
    }

    /**
     *  Provides payer's report editing
     *  @param report the data for report identification
     */
    private void editReportByPayer(String report) throws Exception {
        ReportDTO reportDTO = rc.getTaxpayerReportDTOByLoginAndTime(report);
        reportDTO.setAssessed(false);
        reportDTO.setNote("");
        rc.updateReport(reportDTO);
    }

    /**
     *  Provides complaint creating
     */
    private void complaintReportByPayer() throws Exception {
        ReplacementDTO r = ReplacementDTO.builder()
            .creationTime(LocalDateTime.now())
            .taxofficer(rc.getTaxofficerForTaxpayerLogin(loginFromSecurity()))
            .taxpayer(rc.getTaxpayerByLogin(loginFromSecurity()))
            .build();
        rc.saveNewReplacementRequest(r);
    }

    /**
     * HTTP request handler method of MVC for officer's requests resolving
     * @param report the data for report identification,
     * @param accBtn the solution for report as assessed and accepted,
     * @param reclText the officer's note made during the payer's report assessing,
     * @param reclBtn the solution for report as assessed and not accepted
     */
    @RequestMapping(value = "/officer_report_list/create",
                    method = RequestMethod.POST)
    public String getReportForOfficerFromForm(String report,
                                              String accBtn,
                                              String reclText,
                                              String reclBtn
                                              ) throws Exception {
        ReportDTO reportDTO = rc.getTaxpayerReportDTOByLoginAndTime(report);
        reportDTO.setAssessed(true);
        reportDTO.setTaxofficerLogin(loginFromSecurity());
        if (accBtn != null) {
            reportDTO.setAccepted(true);
            reportDTO.setAcceptTime(LocalDateTime.now());
            rc.acceptReport(reportDTO);
        } else if (reclBtn != null) {
            reportDTO.setAccepted(false);
            reportDTO.setNote(reclText);
            rc.assessReport(reportDTO);
        }
        return "redirect:/officer_report_list";
    }

    /**
     * Provides officer's report data creating
     * @param loginName logged user's name
     */
    private ReportDTO createNewReport(String loginName) {
        return  ReportDTO.builder()
                .taxpayerLogin(loginName)
                .creationTime(LocalDateTime.now())
                .accepted(false)
                .assessed(false)
                .build();
    }

    /**
     * Provides logged user's name getting
     */
    private String loginFromSecurity() throws Exception {
        return SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString();
    }

}
