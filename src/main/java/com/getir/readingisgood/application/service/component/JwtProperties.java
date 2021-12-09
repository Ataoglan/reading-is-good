package com.getir.readingisgood.application.service.component;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "jwt-security")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @ToString
public class JwtProperties {
    private String authHeader;
    private String authHeaderPrefix;
    private String secret;
    private String allowedPaths;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
}
