package com.daisuke.adapters.sonarqube;

import java.net.URL;

import org.sonarqube.ws.client.HttpConnector;
import org.sonarqube.ws.client.WsClient;
import org.sonarqube.ws.client.WsClientFactories;
import org.sonarqube.ws.client.WsConnector;
import org.sonarqube.ws.client.authentication.AuthenticationService;
import org.sonarqube.ws.client.authentication.LoginRequest;
import org.sonarqube.ws.client.components.ComponentsService;
import org.sonarqube.ws.client.issues.IssuesService;
import org.sonarqube.ws.client.measures.MeasuresService;
import org.sonarqube.ws.client.rules.RulesService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.domain.adapters.SearchException;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class SonarQubeClient {
    private WsConnector httpConnector;
    private WsClient wsClient;
    private AuthenticationService authService;
    private LoginRequest loginRequest;
    private IssuesService issuesService;
    private RulesService rulesService;
    private ComponentsService componentsService;
    private MeasuresService measuresService;

    public SonarQubeClient(SonarQubeConfiguration config) {
	URL url = config.getAnalysisServer().getUrl();
	String baseURL = url.toString();
	String token = config.getAnalysisServer().getToken();
	this.httpConnector = HttpConnector.newBuilder().token(token).url(baseURL).build();
	this.wsClient = WsClientFactories.getDefault().newClient(httpConnector);
	this.authService = wsClient.authentication();
	this.loginRequest = new LoginRequest().setLogin(token);
	this.issuesService = wsClient.issues();
	this.rulesService = wsClient.rules();
	this.componentsService = wsClient.components();
	this.measuresService = wsClient.measures();
    }

    /**
     * Perform the login
     */
    public void login() {
	authService.login(loginRequest);
    }

    private boolean credentialsStillValid() throws JSONException {
	boolean result = false;
	String validate = authService.validate();
	JSONObject jo = new JSONObject(validate);
	result = jo.getBoolean("valid");
	return result;
    }

    /**
     * Re-authenticate if the login session has expired
     * 
     * @throws SearchException
     */
    public void refreshConnection() throws SearchException {
	boolean shouldLogin = false;
	try {
	    shouldLogin = !credentialsStillValid();
	} catch (JSONException e) {
	    log.debug(e.getMessage(), e);
	    throw new SearchException("Error while parsing Login response");
	}
	if (shouldLogin) {
	    login();
	}
    }

}
