package ua.testing.registration_form.dto;

import ua.testing.registration_form.entity.Report;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AltReportDTO {

    private LocalDateTime creationTime;

    private String note;

    private boolean accepted;

    private LocalDateTime acceptTime;

    private Report report;

}
