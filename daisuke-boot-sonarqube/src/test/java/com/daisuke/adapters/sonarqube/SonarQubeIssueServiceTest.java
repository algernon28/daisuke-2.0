package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Constants.ISSUE_URL;
import static com.daisuke.adapters.sonarqube.Constants.RULESHOW_URL;
import static com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample.getIssueDTO;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Issues.Issue;
import org.sonarqube.ws.Issues.SearchWsResponse;

import com.daisuke.adapters.sonarqube.samples.IssueData.IssueSample;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.IssueDTO;
import com.daisuke.domain.model.TypeEnum;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.AnythingPattern;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

@TestInstance(Lifecycle.PER_CLASS)
class SonarQubeIssueServiceTest extends AbstractWireMockTest<SonarQubeIssueService, IssueMapper> {
    SonarQubeRulesService rulesService;
    StubMapping rulesMapping;
    UUID rulesID = UUID.randomUUID();

    @BeforeAll
    void setUpBeforeClass() throws Exception {
	super.init(SonarQubeIssueService.class, IssueMapper.class);
	rulesMapping = get(urlPathEqualTo(RULESHOW_URL)).withId(rulesID).withQueryParam("key", new AnythingPattern())
		.build();
	wmServer.addStubMapping(rulesMapping);
	rulesService = new SonarQubeRulesService(client);
	RuleMapper rmapper = Mappers.getMapper(RuleMapper.class);
	rulesService.setMapper(rmapper);
	service.setRulesAdapter(rulesService);
    }

    @AfterAll
    void tearDownAfterClass() throws Exception {
	wmServer.stop();
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    final void testFindIssues() throws SearchException {
	int resultSize = 20;
	TypeEnum type = TypeEnum.CODE_SMELL;
	List<Issue> wsIssues = expectedIssueList(resultSize, type);
	List<IssueDTO> expected = mapper.toIssueDTOList(wsIssues);
	SearchIssue search = new SearchIssue().setTypes(Arrays.asList(type.name()));
	List<String> componentKeys = wsIssues.stream().flatMap(comp -> Stream.of(comp.getKey())).distinct()
		.collect(Collectors.toList());
	mockDTOList(wsIssues, componentKeys);
	search.setComponentKeys(componentKeys);
	List<IssueDTO> actual = service.findIssues(search);
	assertThat(expected).isEqualTo(actual);
    }

    @Test
    final void testFindIssueByKey() throws SearchException {
	String key = Utils.randomString(10, true);
	IssueDTO expected = expectedDTO(key);
	Issue wsIssue = mapper.toWsIssue(expected);
	mockDTO(wsIssue, key);
	IssueDTO actual = service.findIssueByKey(key);
	assertThat(expected).isEqualTo(actual);
    }

    private List<Issue> expectedIssueList(int size, TypeEnum type) {
	RuleType rtype = RuleType.valueOf(type.name());
	Issue prototype = Issue.newBuilder().setType(rtype).build();
	List<Issue> result = IssueSample.getWsIssueList(prototype, size);
	return result;
    }

    private IssueDTO expectedDTO(String key) {
	IssueDTO result = getIssueDTO();
	result = result.setKey(key);
	return result;
    }

    private void mockDTO(Issue wsIssue, String key) {
	SearchWsResponse response = SearchWsResponse.newBuilder().addIssues(wsIssue).build();
	StubMapping mapping = get(urlPathEqualTo(ISSUE_URL)).withQueryParam("issues", new EqualToPattern(key)).build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
    }

    private void mockDTOList(List<Issue> issues, List<String> componentKeys) {
	SearchWsResponse response = SearchWsResponse.newBuilder().addAllIssues(issues).build();
	String componentParams = componentKeys.stream().collect(Collectors.joining(","));
	Map<String, StringValuePattern> params = new HashMap<>();
	params.put("componentKeys", new EqualToPattern(componentParams));
	StubMapping mapping = get(urlPathEqualTo(ISSUE_URL)).withQueryParams(params).build();

	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
    }
}
