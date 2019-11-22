package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Constants.UNIT_TEST_IP_ADDRESS;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.net.URL;

import org.mapstruct.factory.Mappers;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.github.tomakehurst.wiremock.WireMockServer;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class AbstractWireMockTest<S, M> {
    @Getter(value = AccessLevel.PROTECTED)
    private  Class<S> serviceClass;
    @Getter(value = AccessLevel.PROTECTED)
    private  Class<M> mapperClass;
    
    protected SonarQubeClient client;
    protected S service;
    protected WireMockServer wmServer;
    protected M mapper;

    protected void init(Class<S> serviceClass, Class<M> mapperClass) throws Exception {
	this.serviceClass = serviceClass;
	this.mapperClass = mapperClass;
	wmServer = new WireMockServer((options().bindAddress(UNIT_TEST_IP_ADDRESS).dynamicPort()));
	wmServer.start();
	SonarQubeConfiguration config = TestConfig.getConfiguration();
	SonarQubeConfiguration.Server analysisServer = config.getAnalysisServer();
	String uriString = String.format("http://%s:%s", UNIT_TEST_IP_ADDRESS, wmServer.port());
	URL url = new URL(uriString);
	analysisServer.setUrl(url);
	client = new SonarQubeClient(config);
	mapper = Mappers.getMapper(mapperClass);
	service = serviceClass.getConstructor(SonarQubeClient.class).newInstance(client);
	serviceClass.getMethod("setMapper", mapperClass).invoke(service, mapper);
	addAuthenticationStubs();
    }

    protected void addAuthenticationStubs() {
	wmServer.addStubMapping(Constants.loginBuilder.build());
	wmServer.addStubMapping(Constants.validationBuilder.build());
    }

}
