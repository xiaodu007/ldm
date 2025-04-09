package com.research.manager.sysmanager.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix= "security.jwt")
public class SysSecurityProperties {
    @Value("${security.jwt.secretKey}")
    private String secretKey;
    @Value("${security.jwt.ttlMillis}")
    private Long ttlMillis;
    @Value("${security.jwt.tokenHeader}")
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
