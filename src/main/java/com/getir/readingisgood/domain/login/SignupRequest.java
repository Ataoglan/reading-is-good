package com.getir.readingisgood.domain.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SignupRequest {
    @NotEmpty
    private String nameSurname;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String address;
}
