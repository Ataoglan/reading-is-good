package com.getir.readingisgood.domain.login;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LoginResponse {
    private String jwtToken;
    private String refreshToken;
}
