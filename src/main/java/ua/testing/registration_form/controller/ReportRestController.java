package ua.testing.registration_form.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.testing.registration_form.dto.ReportDTO;

import java.util.List;

@Slf4j
@RestController
public class ReportRestController {

    @Autowired
    IReportController rc;

    @RequestMapping(value = "/not_format/reports_payer",
                    method = RequestMethod.GET)
    public List<ReportDTO> loadTaxpayerReports() throws Exception {
        return rc.getTaxpayerReportDTO(loginFromSecurity());
    }

    @RequestMapping(value = "/not_format/reports_officer",
                    method = RequestMethod.GET)
    public List<ReportDTO> loadOfficerReports() throws Exception {
        return rc.getOfficerReportDTO(loginFromSecurity());
    }

    private String loginFromSecurity() throws Exception {
        return SecurityContextHolder.getContext()
               .getAuthentication().getPrincipal().toString();
    }

}
