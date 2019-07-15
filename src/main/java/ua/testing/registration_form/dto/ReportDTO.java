package ua.testing.registration_form.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReportDTO {
    private LocalDateTime creationTime;
    private boolean assessed;
    private boolean accepted;
    private LocalDateTime acceptTime;
    private String taxpayerLogin;
    private String taxpayerName;
    private String taxofficerLogin;
    private String note;
}
