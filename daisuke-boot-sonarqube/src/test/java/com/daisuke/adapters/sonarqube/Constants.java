package com.daisuke.adapters.sonarqube;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;

public final class Constants {
    public static final String UNIT_TEST_IP_ADDRESS = "127.0.0.1";
    public static final String AUTH_LOGIN = "/api/authentication/login";
    public static final String AUTH_VALIDATION = "/api/authentication/validate";
    private static final String JSON_ISVALID = "{\"valid\":true}";
    public static MappingBuilder loginBuilder = post(urlPathEqualTo(AUTH_LOGIN)).willReturn(ok());

    private static final ResponseDefinitionBuilder VALIDATION_RESPONSE = aResponse().withBody(JSON_ISVALID);

    public static MappingBuilder validationBuilder = get(urlPathEqualTo(AUTH_VALIDATION))
	    .willReturn(VALIDATION_RESPONSE);
    public static final String RULESEARCH_URL = "/api/rules/search";
    public static final String RULESHOW_URL = "/api/rules/show";
    public static final String ISSUE_URL = "/api/issues/search";
    public static final String COMPONENTSEARCH_URL = "/api/components/search";
    public static final String COMPONENTSHOW_URL = "/api/components/show";

    private Constants() {
    };

}
