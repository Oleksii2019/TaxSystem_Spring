package ua.testing.registration_form.model.dao.utility;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.model.dao.ReportRepository;
import ua.testing.registration_form.model.entity.Report;
import ua.testing.registration_form.model.entity.Taxofficer;
import ua.testing.registration_form.model.entity.Taxpayer;
import ua.testing.registration_form.model.service.IReportService;

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
        try {
            rr.setReportAsAccepted(report.getId(), report.getAcceptTime(),
                    report.getTaxofficer());
        } catch (RuntimeException ex) {
            log.info("{}", report.getCreationTime());
        }
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
        }
    }

    @Override
    public List<Report> getTaxpayerReports(String taxpayerlogin) {
        List<Report> lr;
        try {
            lr = rr.findByTaxpayerLogin(taxpayerlogin);
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
            throw new IllegalArgumentException("x_DB");
        }
        return lr;
    }

    @Override
    public List<Report> getOfficerReports(String officerLogin) {
        List<Report> lr;
        try {
            lr = rr.findByOfficerLogin(officerLogin);
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
            throw new IllegalArgumentException("x_DB");
        }
        return lr;
    }

    @Override
    public Report getTaxpayerReportByLoginAndTime(String taxpayerLogin,
                                              LocalDateTime dateTime) {
        Report r;
        try {
            r = rr.find_1_ByTaxpayerLoginAndTime(taxpayerLogin, dateTime);
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
            throw new IllegalArgumentException("x_DB");
        }
        return r;
    }

    @Override
    public Taxofficer getTaxofficerForTaxpayerLogin(String login) {
        try {
            return tm.getTaxofficerForTaxpayerLogin(login);
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
            throw new IllegalArgumentException("x_DB");
        }
    }

    @Override
    public Taxpayer getTaxpayerByLogin(String login) {
        try {
            return tm.getTaxpayerByLogin(login);
        } catch (JDBCException e) {
            log.error(e.getCause().toString());
            throw new IllegalArgumentException("x_DB");
        }
    }

}
