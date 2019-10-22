package com.daisuke.adapters.sonarqube.config;

import java.net.URL;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
@ConfigurationProperties(prefix = "daisuke")
public class SonarQubeConfiguration {
    @NonNull
    private String serverType;
    @NonNull
    @NestedConfigurationProperty
    private Server analysisServer;

    @Data
    @NoArgsConstructor
    public static class Server {
	@NonNull
	private URL url;
	@NonNull
	private String token;
    }
    
    

}
