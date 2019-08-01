package ua.testing.registration_form.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString

@Entity
@Table(name="report_alteration")
public class ReportAlteration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "note")
    private String note;

    @Column(name = "accepted")
    @ColumnDefault("false")
    private boolean accepted;

    @Column(name = "accept_time")
    private LocalDateTime acceptTime;

    @ManyToOne
    @JoinColumn(name = "report")
    private Report report;

}
