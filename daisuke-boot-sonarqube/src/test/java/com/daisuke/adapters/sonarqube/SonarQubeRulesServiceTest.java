package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Utils.randomEnumString;
import static com.daisuke.adapters.sonarqube.Utils.randomLanguage;
import static com.daisuke.adapters.sonarqube.Utils.randomString;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Common.RuleStatus;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Common.Severity;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.Rules.SearchResponse;
import org.sonarqube.ws.Rules.Tags;
import org.springframework.integration.util.UUIDConverter;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.LanguageEnum;
import com.daisuke.domain.model.RuleDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import enumerations.RuleEnumerations.REPOSITORIES;
import static com.daisuke.adapters.sonarqube.Constants.*;

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
	ResponseDefinitionBuilder response = aResponse().withBody(new JSONObject().put("valid", true).toString());
	MappingBuilder searchBuilder = post(urlPathEqualTo(RULE_URL)).willReturn(response);
	wmServer.addStubMapping(Constants.loginBuilder.build());
	wmServer.addStubMapping(Constants.validationBuilder.build());
	wmServer.addStubMapping(searchBuilder.withId(UUIDConverter.getUUID("search")).build());
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

    private Rule randomRule() {
	String desc = randomString(50, true);
	String key = randomString(10, true);
	LanguageEnum lang = randomLanguage();
	String name = randomString(15, false);
	String repo = randomEnumString(REPOSITORIES.values());
	String severity = randomEnumString(Severity.values());
	RuleType type = RuleType.valueOf(randomEnumString(RuleType.values()));
	RuleStatus status = RuleStatus.valueOf(randomEnumString(RuleStatus.values()));
	Tags tags = Tags.newBuilder().addTags("tag 1").addTags("tag 2").build();
	Rule rule = Rule.newBuilder().setCreatedAt(Utils.randomDate(2018, 2019)).setGapDescription("XYZ")
		.setHtmlDesc(desc).setKey(key).setLang(lang.key()).setLangName(lang.description()).setName(name)
		.setRepo(repo).setSeverity(severity).setStatus(status).setTags(tags).setType(type).build();
	return rule;
    }

    private List<Rule> prepareRuleData(int size) {
	List<Rule> rules = new ArrayList<>();
	for (int i = 0; i < size; i++) {
	    rules.add(randomRule());
	}
	return rules;
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
	List<Rule> rules = prepareRuleData(resultSize);
	List<String> languages = rules.stream().flatMap(rule -> Stream.of(rule.getLang())).collect(Collectors.toList());
	SearchResponse response = SearchResponse.newBuilder().addAllRules(rules).setTotal(resultSize).build();
	SearchRule search = new SearchRule().setLanguages(languages);
	String langParam = languages.stream().collect(Collectors.joining(","));
	StubMapping mapping = get(urlPathEqualTo(RULE_URL)).withQueryParam("languages", new EqualToPattern(langParam))
		.build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
	List<RuleDTO> actual = rulesService.findRules(search);
	List<RuleDTO> expected = mapper.toRuleDTOList(rules);
	assertThat(expected).isEqualTo(actual);

    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#findRuleByKey(java.lang.String)}.
     * @throws SearchException 
     */
    @Test
    final void testFindRuleByKey() throws SearchException {
	Rule rule = randomRule();
	String ruleKey = rule.getKey();
	StubMapping mapping = get(urlPathEqualTo(RULE_URL)).withQueryParam("rule_key", new EqualToPattern(ruleKey))
		.build();
	SearchResponse response = SearchResponse.newBuilder().addRules(rule).setTotal(1L).build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
	RuleDTO actual = rulesService.findRuleByKey(ruleKey);
	RuleDTO expected = mapper.toRuleDTO(rule);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeRulesService#SonarQubeRulesService()}.
     */
    @Test
    final void testSonarQubeRulesService() {
	SonarQubeRulesService service = new SonarQubeRulesService();
	assertThat(service).isNotNull();
    }

}
