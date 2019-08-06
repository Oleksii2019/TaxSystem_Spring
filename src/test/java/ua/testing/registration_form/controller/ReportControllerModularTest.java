package ua.testing.registration_form.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.testing.registration_form.dto.ReplacementDTO;
import ua.testing.registration_form.dto.ReportDTO;
import ua.testing.registration_form.model.entity.RoleType;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportControllerModularTest {
    @Autowired
    private ReportController controller;

    @MockBean
    private IReportController rc;

    @Before
    public void setup() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        "1111",
                        AuthorityUtils.createAuthorityList
                                (RoleType.ROLE_PUSER.toString()))
        );
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void createReportByPayerTest() throws Exception {
        String createBtn = "";
        String complaintBtn = null;
        String editBtn = null;
        String report = "";

        String res = controller.modifyReportByPayer(createBtn,
                complaintBtn, editBtn, report);
        Mockito.verify(rc, Mockito.times(1))
               .saveNewReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(0))
               .updateReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(0))
               .saveNewReplacementRequest(ArgumentMatchers
               .any(ReplacementDTO.class));
        Assert.assertEquals("redirect:/payer_report_list", res);
    }

    @Test
    public void complaintReportByPayerTest() throws Exception {
        String createBtn = null;
        String complaintBtn = "";
        String editBtn = null;
        String report = "";

        String res = controller.modifyReportByPayer(createBtn,
                complaintBtn, editBtn, report);

        Mockito.verify(rc, Mockito.times(0))
               .saveNewReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(0))
               .updateReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(1))
               .saveNewReplacementRequest(ArgumentMatchers
               .any(ReplacementDTO.class));
        Assert.assertEquals("redirect:/payer_report_list", res);
    }

    @Test
    public void editReportByPayerTest() throws Exception {
        String createBtn = null;
        String complaintBtn = null;
        String editBtn = "";
        String report = "";
        Mockito.when(rc.getTaxpayerReportDTOByLoginAndTime(
                ArgumentMatchers.anyString())).thenReturn(new ReportDTO());

        String res = controller.modifyReportByPayer(createBtn,
                complaintBtn, editBtn, report);

        Mockito.verify(rc, Mockito.times(0))
               .saveNewReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(1))
               .updateReport(ArgumentMatchers.any(ReportDTO.class));
        Mockito.verify(rc, Mockito.times(0))
               .saveNewReplacementRequest(ArgumentMatchers.any(ReplacementDTO.class));
        Assert.assertEquals("redirect:/payer_report_list", res);
    }

}
