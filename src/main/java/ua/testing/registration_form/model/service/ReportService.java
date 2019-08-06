package ua.testing.registration_form.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.testing.registration_form.controller.IReportController;
import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.model.dao.ReplacementRequestRepository;
import ua.testing.registration_form.model.entity.*;
import ua.testing.registration_form.dto.ReplacementDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ReportService implements IReportController {

    @Autowired
    IReportService rs;

    @Autowired
    IAltReportService ars;

    @Autowired
    ReplacementRequestRepository rrr;

    @Override
    public void saveNewReport(ReportDTO reportDTO) {
        rs.saveNewReport(reportDTO);
    }

    @Override
    public List<ReportDTO> getTaxpayerReportDTO(String taxpayerlogin)
            throws RuntimeException {
        List<Report> lr = rs.getTaxpayerReports(taxpayerlogin);
        List<ReportDTO> lrDTO = new ArrayList<>();
        IntStream.range(0, lr.size()).forEach(i ->
                lrDTO.add(ReportDTO.builder()
                     .creationTime(lr.get(i).getCreationTime())
                     .assessed(lr.get(i).isAssessed())
                     .accepted(lr.get(i).isAccepted())
                     .acceptTime(lr.get(i).getAcceptTime())
                     .taxpayerLogin(lr.get(i).getTaxpayer().getLogin())
                     .taxofficerLogin(lr.get(i).getTaxofficer().getLogin())
                     .note(getNoteFromAltReports(lr, i))
                     .build()
                )
        );
        return lrDTO;
    }

    public String getNoteFromAltReports(List<Report> lr, int i) {
        String str;
        if (lr.get(i).getReportAlterations().stream()
                .anyMatch(c -> !c.isAccepted())) {
            str = lr.get(i).getReportAlterations().stream()
                    .filter(c -> !c.isAccepted())
                    .findFirst().get().getNote();
        } else {
            str = "";
        }
        return str;
    }

    @Override
    public List<ReportDTO> getOfficerReportDTO(String officerlogin) {
        List<Report> lr = rs.getOfficerReports(officerlogin);
        List<ReportDTO> lrDTO = new ArrayList<>();
        IntStream.range(0, lr.size()).forEach(i ->
                lrDTO.add(ReportDTO.builder()
                        .creationTime(lr.get(i).getCreationTime())
                        .assessed(lr.get(i).isAssessed())
                        .accepted(lr.get(i).isAccepted())
                        .acceptTime(lr.get(i).getAcceptTime())
                        .taxpayerLogin(lr.get(i).getTaxpayer().getLogin())
                        .taxofficerLogin(lr.get(i).getTaxofficer().getLogin())
                        .taxpayerName(lr.get(i).getTaxpayer().getName())
                        .build()
                )
        );
        return lrDTO;
    }

    @Override
    public ReportDTO getTaxpayerReportDTOByLoginAndTime(String loginAndTime) {
        String[] arr = loginAndTime.split(":-");
        Report r = rs.getTaxpayerReportByLoginAndTime(
                arr[0], LocalDateTime.parse(arr[1]));
        return ReportDTO.builder()
               .creationTime(r.getCreationTime())
               .assessed(r.isAssessed())
               .accepted(r.isAccepted())
               .acceptTime(r.getAcceptTime())
               .acceptTime(r.getAcceptTime())
               .taxpayerLogin(r.getTaxpayer().getLogin())
               .taxofficerLogin(r.getTaxofficer().getLogin())
               .taxpayerName(r.getTaxpayer().getName())
               .build();
    }

    @Override
    @Transactional
    public void updateReport(ReportDTO reportDTO) {
        Report report = rs.getTaxpayerReportByLoginAndTime(
                reportDTO.getTaxpayerLogin(),
                reportDTO.getCreationTime());
        ReportAlteration ra = ars.getAltNotAcceptedReportForReport(report);
        rs.setReportAsNotAssessed(report);
        ars.setAltReportAsAccepted(ra);
    }

    @Override
    @Transactional
    public void acceptReport(ReportDTO reportDTO) {
        Report report = rs.getTaxpayerReportByLoginAndTime(
                reportDTO.getTaxpayerLogin(),
                reportDTO.getCreationTime());
        report.setAcceptTime(reportDTO.getAcceptTime());
        rs.setReportAsAccepted(report);
    }

    @Override
    @Transactional
    public void assessReport(ReportDTO reportDTO) {
        Report report = rs.getTaxpayerReportByLoginAndTime(
                reportDTO.getTaxpayerLogin(),
                reportDTO.getCreationTime());
        AltReportDTO arDTO = AltReportDTO.builder()
                .creationTime(LocalDateTime.now())
                .note(reportDTO.getNote())
                .report(report)
                .build();
        rs.setReportAsAssessed(report);
        ars.saveNewAltReport(arDTO);
    }

    @Override
    public Taxofficer getTaxofficerForTaxpayerLogin(String login) {
        return rs.getTaxofficerForTaxpayerLogin(login);
    }

    @Override
    public Taxpayer getTaxpayerByLogin(String login) {
        return rs.getTaxpayerByLogin(login);
    }

    @Override
    public void saveNewReplacementRequest(ReplacementDTO replacementDTO) {
        if (rrr.findByTaxofficerAndTaxpayer(replacementDTO.getTaxofficer(),
                replacementDTO.getTaxpayer()).size() == 0) {
            rrr.save(ReplacementRequest.builder()
                    .creationTime(replacementDTO.getCreationTime())
                    .taxofficer(replacementDTO.getTaxofficer())
                    .taxpayer(replacementDTO.getTaxpayer())
                    .build()
            );
        }
    }

}
