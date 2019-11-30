/**
 * 
 */
package com.daisuke.controllers.sonarqube;

import java.net.URL;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Andrea M.
 *
 */
@Data
@NoArgsConstructor
@Validated
@ConfigurationProperties(prefix = "daisuke")
public class SonarQubeConfiguration {

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
