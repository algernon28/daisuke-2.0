package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Constants.RULESEARCH_URL;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.Rules.SearchResponse;
import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.RuleDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

/**
 * @author Andrea M.
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class SonarQubeRulesServiceTest {
    private SonarQubeClient client;
    private SonarQubeRulesService rulesService;
    private WireMockServer wmServer;
    private RuleMapper mapper;

    private void wireMockInit() throws JSONException {
	wmServer.addStubMapping(Constants.loginBuilder.build());
	wmServer.addStubMapping(Constants.validationBuilder.build());
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	wmServer = new WireMockServer((options().bindAddress("127.0.0.1").dynamicPort()));
	wmServer.start();
	SonarQubeConfiguration config = TestConfig.getConfiguration();
	SonarQubeConfiguration.Server analysisServer = config.getAnalysisServer();
	String uriString = String.format("http://127.0.0.1:%s", wmServer.port());
	URL url = new URL(uriString);
	analysisServer.setUrl(url);
	client = new SonarQubeClient(config);
	mapper = Mappers.getMapper(RuleMapper.class);
	rulesService = new SonarQubeRulesService(client);
	rulesService.setRuleMapper(mapper);
	wireMockInit();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    static void tearDownAfterClass() throws Exception {
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
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#SonarQubeRulesService(com.daisuke.adapters.sonarqube.SonarQubeClient)}.
     */
    @Test
    final void testSonarQubeRulesServiceSonarQubeClient() {
	assertThat(rulesService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#setComponentMapper(com.daisuke.adapters.sonarqube.RuleMapper)}.
     */
    @Test
    final void testSetComponentMapper() {
	SonarQubeRulesService service = new SonarQubeRulesService();
	service.setRuleMapper(mapper);
	assertThat(mapper).isEqualTo(service.getRuleMapper());
    }
    
    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#findRules(com.daisuke.adapters.sonarqube.SearchRule)}.
     * 
     * @throws SearchException
     */
    @Test
    final void testFindRules() throws SearchException {
	int resultSize = 20;
	List<Rule> wsRules = RuleSample.getWsRuleList(resultSize);
	List<String> languages = wsRules.stream().flatMap(rule -> Stream.of(rule.getLang())).collect(Collectors.toList());
	SearchResponse response = SearchResponse.newBuilder().addAllRules(wsRules).setTotal(resultSize).build();
	SearchRule search = new SearchRule().setLanguages(languages);
	String langParam = languages.stream().collect(Collectors.joining(","));
	StubMapping mapping = get(urlPathEqualTo(RULESEARCH_URL)).withQueryParam("languages", new EqualToPattern(langParam))
		.build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
	List<RuleDTO> actual = rulesService.findRules(search);
	List<RuleDTO> expected = mapper.toRuleDTOList(wsRules);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#findRuleByKey(java.lang.String)}.
     * @throws SearchException 
     */
    @Test
    final void testFindRuleByKey() throws SearchException {
	Rule wsRule = RuleSample.getWsRule();
	String ruleKey = wsRule.getKey();
	StubMapping mapping = get(urlPathEqualTo(RULESEARCH_URL)).withQueryParam("rule_key", new EqualToPattern(ruleKey))
		.build();
	SearchResponse response = SearchResponse.newBuilder().addRules(wsRule).setTotal(1L).build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
	RuleDTO actual = rulesService.findRuleByKey(ruleKey);
	RuleDTO expected = mapper.toRuleDTO(wsRule);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#SonarQubeRulesService()}.
     */
    @Test
    final void testSonarQubeRulesService() {
	Optional<SonarQubeRulesService> service = Optional.of(new SonarQubeRulesService());
	assertThat(service).isPresent();
    }

}
