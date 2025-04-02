package com.research.manager.ldm.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix= "security.jwt")
public class SysSecurityProperties {
    private String secretKey;
    private Long ttlMillis;
    private String tokenHeader;

    public String getSecretKey(){
        return secretKey;
    }

    public Long getTtlMillis() {
        return ttlMillis;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }
}
