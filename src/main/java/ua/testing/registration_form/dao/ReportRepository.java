package ua.testing.registration_form.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.testing.registration_form.entity.Report;

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
    List<Report>  findByTaxpayerLoginAndTime(@Param("login") String taxpayerLogin,
                                             @Param("time") LocalDateTime createTime);
}
