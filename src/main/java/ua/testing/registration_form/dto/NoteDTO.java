package ua.testing.registration_form.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class NoteDTO {

    private String names;

    private String login;

}
