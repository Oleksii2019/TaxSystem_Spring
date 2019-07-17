package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.controller.IReportController;
import ua.testing.registration_form.dao.ReplacementRequestRepository;
import ua.testing.registration_form.dto.AltReportDTO;
import ua.testing.registration_form.dto.ReplacementDTO;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.entity.*;

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
    public List<ReportDTO> getTaxpayerReportDTO(String taxpayerlogin) throws RuntimeException {
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
        List<Report> lr = rs.getTaxpayerReportByLoginAndTime(
                arr[0], LocalDateTime.parse(arr[1]));
        return ReportDTO.builder()
               .creationTime(lr.get(0).getCreationTime())
               .assessed(lr.get(0).isAssessed())
               .accepted(lr.get(0).isAccepted())
               .acceptTime(lr.get(0).getAcceptTime())
               .acceptTime(lr.get(0).getAcceptTime())
               .taxpayerLogin(lr.get(0).getTaxpayer().getLogin())
               .taxofficerLogin(lr.get(0).getTaxofficer().getLogin())
               .taxpayerName(lr.get(0).getTaxpayer().getName())
               .build();
    }

    @Override
    public void updateReport(ReportDTO reportDTO) {
        List<Report> lr = rs.getTaxpayerReportByLoginAndTime(
                reportDTO.getTaxpayerLogin(),
                reportDTO.getCreationTime());

        List<ReportAlteration> ral = ars.getAltReportsForReport(lr.get(0));

        if (!reportDTO.isAssessed() &&
                ral.stream().anyMatch(c -> !c.isAccepted())) {
            ReportAlteration ra = ral.stream()
                    .filter(c -> !c.isAccepted())
                    .findFirst().get();
            ra.setAccepted(true);
            ra.setAcceptTime(LocalDateTime.now());
        }

        rs.deleteReport(reportDTO);
        rs.saveNewReport(reportDTO);

        lr = rs.getTaxpayerReportByLoginAndTime(
                reportDTO.getTaxpayerLogin(),
                reportDTO.getCreationTime());

        ars.deleteAltReports(ral);

        Report report = lr.get(0);
        IntStream.range(0, ral.size()).forEach(i -> ral.get(i).setReport(report));

        ars.saveAltReports(ral);

        if (!reportDTO.isAccepted()) {
            AltReportDTO arDTO = AltReportDTO.builder()
                .creationTime(LocalDateTime.now())
                .note(reportDTO.getNote())
                .report(report)
                .build();
            ars.saveNewAltReport(arDTO);
        }
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
        rrr.save(ReplacementRequest.builder()
            .creationTime(replacementDTO.getCreationTime())
            .taxofficer(replacementDTO.getTaxofficer())
            .taxpayer(replacementDTO.getTaxpayer())
            .build()
        );
    }

}
