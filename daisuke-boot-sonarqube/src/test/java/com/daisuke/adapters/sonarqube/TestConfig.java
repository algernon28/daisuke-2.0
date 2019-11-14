package com.daisuke.adapters.sonarqube;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.annotation.Bean;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestConfig {

    @Bean
    public static SonarQubeConfiguration getConfiguration() {
	SonarQubeConfiguration.Server server = new SonarQubeConfiguration.Server();
	URL serverURL = null;
	try {
	    serverURL = new URL("https://next.sonarqube.com/sonarqube");
	} catch (MalformedURLException e) {
	    log.error(e.getMessage(), e);
	}
	server.setToken("abc");
	server.setUrl(serverURL);
	SonarQubeConfiguration config = new SonarQubeConfiguration();
	config.setAnalysisServer(server);
	config.setServerType("SonarQube");
	return config;
    }
}
