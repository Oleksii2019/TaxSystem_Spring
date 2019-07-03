package ua.testing.registration_form.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String login;
    private String password;
}
