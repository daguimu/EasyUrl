package com.dagm.shorter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Guimu
 * @date 2020/01/03
 */
@Component
@ConfigurationProperties(prefix = "spring.easy.url")
@Getter
@Setter
public class ShorterConfig {
    /**
     * Short link validity seed code.
     */
    private String bitStr;
    /**
     * Default address when the short link does not exist.
     */
    private String defaultUrl;

    /**
     * Generate the service domain name for the short link.
     */
    private String baseUrl;
}