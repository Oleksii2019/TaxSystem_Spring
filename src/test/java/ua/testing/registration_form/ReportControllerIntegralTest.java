package ua.testing.registration_form;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerIntegralTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void commonAccessToMainPage() throws Exception {
        this.mockMvc.perform(post("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void commonAccessToSecurityPage() throws Exception {
        this.mockMvc.perform(post("/payer_report_list"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
