package ua.testing.registration_form.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.dao.ReportRepository;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.service.IReportService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class ReportManager implements IReportService {

    @Autowired
    ReportRepository rr;

    @Autowired
    TaxpayerManager tm;

    @Override
    public void setReportAsAccepted(Report report) {
        rr.setReportAsAccepted(report.getId(), report.getAcceptTime(),
                               report.getTaxofficer());
    }

    @Override
    public void setReportAsNotAssessed(Report report) {
        rr.setReportAsAssessed(report.getId());
    }

    @Override
    public void setReportAsAssessed(Report report) {
        rr.setReportAsAssessed(report.getId(), report.getTaxofficer());
    }

    @Override
    public void saveNewReport(ReportDTO reportDTO) throws RuntimeException {
        Report report = Report.builder()
                .taxpayer(tm.getTaxpayerByLogin(reportDTO.getTaxpayerLogin()))
                .taxofficer(tm.getTaxofficerForTaxpayerLogin(reportDTO.getTaxpayerLogin()))
                .creationTime(reportDTO.getCreationTime())
                .assessed(reportDTO.isAssessed())
                .accepted(reportDTO.isAccepted())
                .acceptTime(reportDTO.getAcceptTime())
                .build();
        try {
            rr.save(report);
        } catch (RuntimeException ex) {
            log.info("{}", report.getCreationTime());
            throw new IllegalArgumentException("x_DB");
        }
    }

    @Override
    public void deleteReport(ReportDTO reportDTO) {
        try {
            List<Report> lr = rr.findByTaxpayerLoginAndTime(
                    reportDTO.getTaxpayerLogin(),
                    reportDTO.getCreationTime());
            IntStream.range(0, lr.size())
                    .forEach(i ->
                    rr.deleteById(lr.get(i).getId()));
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("x_DB");
        }
    }

    @Override
    public List<Report> getTaxpayerReports(String taxpayerlogin) {
        List<Report> lr;
        try {
            lr = rr.findByTaxpayerLogin(taxpayerlogin);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("x_DB");
        }
        return lr;
    }

    @Override
    public List<Report> getOfficerReports(String officerLogin) {
        List<Report> lr;
        try {
            lr = rr.findByOfficerLogin(officerLogin);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("x_DB");
        }
        return lr;
    }

    @Override
    public List<Report> getTaxpayerReportByLoginAndTime(String taxpayerLogin, LocalDateTime dateTime) {
        List<Report> lr;
        try {
            lr = rr.findByTaxpayerLoginAndTime(taxpayerLogin, dateTime);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("x_DB");
        }
        return lr;
    }

    @Override
    public Report get_1_TaxpayerReportByLoginAndTime(String taxpayerLogin,
                                              LocalDateTime dateTime) {
        Report r;
        try {
            r = rr.find_1_ByTaxpayerLoginAndTime(taxpayerLogin, dateTime);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("x_DB");
        }
        return r;
    }

    @Override
    public Taxofficer getTaxofficerForTaxpayerLogin(String login) {
        return tm.getTaxofficerForTaxpayerLogin(login);
    }

    @Override
    public Taxpayer getTaxpayerByLogin(String login) {
        return tm.getTaxpayerByLogin(login);
    }

}
