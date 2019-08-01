package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.testing.registration_form.entity.Report;
import ua.testing.registration_form.entity.Taxofficer;

import java.time.LocalDateTime;
import java.util.List;


public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select u from Report u where u.accepted = false and "
           + "u.taxpayer.login = :login")
    List<Report> findByTaxpayerLogin(@Param("login") String taxpayerlogin);

    @Query("select u from Report u where u.accepted = false and "
           + "u.assessed = false and u.taxofficer.login = :login")
    List<Report> findByOfficerLogin(@Param("login") String officerLogin);

    @Query("select u from Report u where u.taxpayer.login = :login and "
            + "u.creationTime = :time")
    List <Report>  findByTaxpayerLoginAndTime(@Param("login") String taxpayerLogin,
                                             @Param("time") LocalDateTime createTime);

    @Query("select u from Report u where u.taxpayer.login = :login and "
            + "u.creationTime = :time")
    Report  find_1_ByTaxpayerLoginAndTime(@Param("login") String taxpayerLogin,
                                              @Param("time") LocalDateTime createTime);

    @Modifying
    @Query("update Report u set u.assessed = true, u.taxofficer = :taxofficer "
            + "where u.id = :id")
    void setReportAsAssessed(@Param("id") Long reportId,
                             @Param("taxofficer") Taxofficer taxoffiser);

    @Modifying
    @Query("update Report u set u.assessed = false "
            + "where u.id = :reportId")
    void setReportAsAssessed(@Param("reportId") Long reportId);

    @Modifying
    @Query("update Report u set u.accepted = true, u.acceptTime = "
            + ":acceptTime, u.taxofficer = :taxofficer where u.id = :reportId")
    void setReportAsAccepted(@Param("reportId") Long reportId,
                             @Param("acceptTime") LocalDateTime acceptTime,
                             @Param("taxofficer") Taxofficer taxofficer);
}
