package ua.testing.registration_form.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name="taxpayers")
//       uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Taxpayer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

//    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType role;

    @ManyToOne
    @JoinColumn(name = "taxofficer")
    private  Taxofficer taxofficer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxpayer")
    private Set<Report> reports;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxpayer")
    private Set<ReplacementRequest> replacementRequests;


}
