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
@Table(name="taxofficers")
public class Taxofficer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxofficer")
    private Set<Taxpayer> taxpayers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxofficer")
    private Set<Report> reports;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taxofficer")
    private Set<ReplacementRequest> replacementRequests;

}
