package ua.testing.registration_form.dto;

import ua.testing.registration_form.entity.Taxofficer;
import ua.testing.registration_form.entity.Taxpayer;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReplacementDTO {
    private LocalDateTime creationTime;
    private Taxpayer taxpayer;
    private Taxofficer taxofficer;
}
