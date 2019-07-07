package ua.testing.registration_form.entity;

import lombok.*;

import javax.persistence.*;

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

}
