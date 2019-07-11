package ua.testing.registration_form.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name="reports")

public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "assessed")
    @org.hibernate.annotations.ColumnDefault("false")
    private boolean assessed;

    @Column(name = "accepted")
    @org.hibernate.annotations.ColumnDefault("false")
    private boolean accepted;

    @Column(name = "accept_time")
    private LocalDateTime acceptTime;

    @ManyToOne
    @JoinColumn(name = "taxpayer")
    private Taxpayer taxpayer;

    @ManyToOne
    @JoinColumn(name = "taxofficer")
    private Taxofficer taxofficer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report")
    private Set<ReportAlteration> reportAlterations;

}
