package com.daisuke.adapters.sonarqube;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.sonarqube.ws.client.authentication.AuthenticationService;
import org.sonarqube.ws.client.authentication.LoginRequest;
import org.sonarqube.ws.client.components.ComponentsService;
import org.sonarqube.ws.client.issues.IssuesService;
import org.sonarqube.ws.client.measures.MeasuresService;
import org.sonarqube.ws.client.rules.RulesService;
import org.springframework.integration.util.UUIDConverter;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.domain.adapters.SearchException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import static com.daisuke.adapters.sonarqube.Constants.*;
/**
 * @author Andrea M.
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class SonarQubeClientTest {
    private SonarQubeClient client;
    private AuthenticationService authService;
    private LoginRequest loginRequest;
    private IssuesService issuesService;
    private RulesService rulesService;
    private ComponentsService componentsService;
    private MeasuresService measuresService;
    private WireMockServer wmServer;


    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	wireMockInit();
	SonarQubeConfiguration config = TestConfig.getConfiguration();
	URL url = new URL(wmServer.baseUrl());
	config.getAnalysisServer().setUrl(url);
	client = new SonarQubeClient(config);
	authService = client.getAuthService();
	loginRequest = client.getLoginRequest();
	issuesService = client.getIssuesService();
	rulesService = client.getRulesService();
	componentsService = client.getComponentsService();
	measuresService = client.getMeasuresService();
    }
    
    private void wireMockInit() throws JSONException {
	wmServer = new WireMockServer((options().bindAddress("127.0.0.1").dynamicPort()));
	wmServer.start();
	MappingBuilder loginBuilder = post(urlPathEqualTo(AUTH_LOGIN)).willReturn(ok());
	ResponseDefinitionBuilder validationResponse = aResponse()
		.withBody(new JSONObject().put("valid", true).toString());
	MappingBuilder validationBuilder = get(urlPathEqualTo(AUTH_VALIDATION))
		.willReturn(validationResponse);
	wmServer.addStubMapping(validationBuilder.withId(UUIDConverter.getUUID("validate")).build());
	wmServer.addStubMapping(loginBuilder.withId(UUIDConverter.getUUID("login")).build());
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    void tearDownAfterClass() throws Exception {
	wmServer.stop();
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#SonarQubeClient(com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration)}.
     */
    @Test
    final void testSonarQubeClient() {
	assertThat(client).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#login()}.
     * 
     * @throws JSONException
     */
    @Test
    final void testLogin() throws JSONException {
	client.login();
	JSONObject jobj = new JSONObject(authService.validate());
	assertThat(checkAuthentication(jobj)).isTrue();
    }

    private boolean checkAuthentication(JSONObject json) throws JSONException {
	return json.getBoolean("valid");
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#refreshConnection()}.
     * 
     * @throws SearchException
     * @throws JSONException
     */
    @Test
    final void testRefreshConnection() throws SearchException, JSONException {
	client.refreshConnection();
	JSONObject jobj = new JSONObject(authService.validate());
	assertThat(checkAuthentication(jobj)).isTrue();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getHttpConnector()}.
     */
    @Test
    final void testGetHttpConnector() {
	assertThat(client.getHttpConnector()).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getWsClient()}.
     */
    @Test
    final void testGetWsClient() {
	assertThat(client.getWsClient()).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getAuthService()}.
     */
    @Test
    final void testGetAuthService() {
	assertThat(authService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getLoginRequest()}.
     */
    @Test
    final void testGetLoginRequest() {
	assertThat(loginRequest).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getIssuesService()}.
     */
    @Test
    final void testGetIssuesService() {
	assertThat(issuesService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getRulesService()}.
     */
    @Test
    final void testGetRulesService() {
	assertThat(rulesService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getComponentsService()}.
     */
    @Test
    final void testGetComponentsService() {
	assertThat(componentsService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeClient#getMeasuresService()}.
     */
    @Test
    final void testGetMeasuresService() {
	assertThat(measuresService).isNotNull();
    }

}
