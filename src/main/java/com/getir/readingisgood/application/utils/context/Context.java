package com.getir.readingisgood.application.utils.context;

import lombok.*;

import java.util.UUID;

@Getter
@Setter @NoArgsConstructor
@AllArgsConstructor
@Builder @ToString
public class Context {
    private String email;
    private String token;

    @Builder.Default
    private String transactionId = UUID.randomUUID().toString();
}

