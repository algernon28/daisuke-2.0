package com.daisuke.adapters.sonarqube;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Constants {
    public static final String AUTH_LOGIN = "/api/authentication/login";
    public static final String AUTH_VALIDATION = "/api/authentication/validate";
    public static MappingBuilder loginBuilder = post(urlPathEqualTo(AUTH_LOGIN)).willReturn(ok());

    private static ResponseDefinitionBuilder validationResponse() {
	ResponseDefinitionBuilder result = null;
	try {
	    result = aResponse().withBody(new JSONObject().put("valid", true).toString());
	} catch (JSONException e) {
	    // shouldn't get here anyways
	    log.debug(e.getMessage());
	}
	return result;
    };

    public static MappingBuilder validationBuilder = get(urlPathEqualTo(AUTH_VALIDATION))
	    .willReturn(validationResponse());

    private Constants() {
    };
    public static final String RULE_URL = "/api/rules/search";
}
