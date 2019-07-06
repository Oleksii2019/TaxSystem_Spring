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
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String login;

    private String password;

//    @Enumerated(EnumType.STRING)
    private RoleType role;

}
