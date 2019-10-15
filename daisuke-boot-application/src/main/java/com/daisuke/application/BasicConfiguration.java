package com.daisuke.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.NoArgsConstructor;

@Configuration
@EnableConfigurationProperties(ConfigurationData.class)
@NoArgsConstructor
public class BasicConfiguration {
    @SuppressWarnings("unused")
    private ConfigurationData config;

    @Autowired
    public void setConfig(ConfigurationData config) {
	this.config = config;
    }

    @Bean
    public RestTemplate getRestTemplate() {
	return new RestTemplate();
    }
}
