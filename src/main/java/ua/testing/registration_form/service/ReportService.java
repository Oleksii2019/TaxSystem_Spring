package ua.testing.registration_form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.registration_form.controller.IReportController;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.entity.Report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ReportService implements IReportController {

    @Autowired
    IReportService rs;

    @Override
    public void saveNewReport(ReportDTO reportDTO) {
        rs.saveNewReport(reportDTO);
    }

    @Override
    public List<ReportDTO> getTaxpayerReportDTO(String taxpayerlogin) {
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
                     .build()
                )
        );
        return lrDTO;
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
        rs.deleteReport(reportDTO);
        rs.saveNewReport(reportDTO);
        // TODO Update or/and add report_alternation
        if (reportDTO.isAccepted()) {
            // Update
        } else {
            // Update and add
        }
    }


}
