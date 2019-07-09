package ua.testing.registration_form.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name="replacement_request")
public class ReplacementRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "taxplayer", nullable = false)
    private  Taxpayer taxpayer;

    @ManyToOne
    @JoinColumn(name = "taxofficer", nullable = false)
    private  Taxofficer taxofficer;

}
