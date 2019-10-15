package com.daisuke.application;

import java.net.URL;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
@ConfigurationProperties(prefix="daisuke")
public class ConfigurationData {
    @NotNull
    private String serverType;
    @NotNull
    @NestedConfigurationProperty
    private Server analysisServer;
    @NotNull
    @NestedConfigurationProperty
    private Output output;

    @Data
    @NoArgsConstructor
    public static class Server {
	@NotNull
	private URL url;
	@NotNull
	private String token;
    }

    @Data
    @NoArgsConstructor
    public static class Output {
	@NotNull
	private String path;
    }

}
