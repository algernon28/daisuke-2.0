/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Constants.*;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.Components.SearchWsResponse;
import org.sonarqube.ws.client.components.ComponentsService;
import org.sonarqube.ws.client.components.SearchRequest;

import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.adapters.sonarqube.samples.ComponentData.ComponentSample;
import com.daisuke.adapters.sonarqube.samples.ComponentData.SearchSample;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.ComponentDTO;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

/**
 * @author Andrea M.
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class SonarQubeComponentServiceTest {
    private SonarQubeClient client;
    private SonarQubeComponentService componentService;
    private WireMockServer wmServer;
    private ComponentMapper mapper;

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
	mapper = Mappers.getMapper(ComponentMapper.class);
	componentService = new SonarQubeComponentService(client);
	componentService.setComponentMapper(mapper);
	wireMockInit();
    }

    private void wireMockInit() throws JSONException {
	wmServer.addStubMapping(Constants.loginBuilder.build());
	wmServer.addStubMapping(Constants.validationBuilder.build());
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterAll
    void tearDownAfterClass() throws Exception {
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
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#SonarQubeComponentService(com.daisuke.adapters.sonarqube.SonarQubeClient)}.
     */
    @Test
    final void testSonarQubeComponentServiceSonarQubeClient() {
	assertThat(componentService).isNotNull();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#setComponentMapper(com.daisuke.adapters.sonarqube.ComponentMapper)}.
     */
    @Test
    final void testSetComponentMapper() {
	assertThat(mapper).isEqualTo(componentService.getComponentMapper());
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#findComponents(com.daisuke.adapters.sonarqube.SearchComponent)}.
     * 
     * @throws SearchException
     */
    @Test
    final void testFindComponents() throws SearchException {
	int resultSize = 20;
	String language = "java";
	// create golden data
	List<Component> wsComponents = createExpectedComponents(resultSize, language);
	List<ComponentDTO> expected = mapper.toComponentDTOList(wsComponents);
	List<String> qualifiers = wsComponents.stream().flatMap(comp -> Stream.of(comp.getQualifier())).distinct()
		.collect(Collectors.toList());
	SearchComponent search = new SearchComponent().setLanguage(language).setQualifiers(qualifiers);
	// execute tested method to find actual data
	mockDTOList(wsComponents, qualifiers, language);
	List<ComponentDTO> actual = componentService.findComponents(search);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#findComponentByKey(java.lang.String)}.
     */
    @Test
    final void testFindComponentByKey() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#SonarQubeComponentService()}.
     */
    @Test
    final void testSonarQubeComponentService() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#getComponentMapper()}.
     */
    @Test
    final void testGetComponentMapper() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#getClient()}.
     */
    @Test
    final void testGetClient() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#getComponentsService()}.
     */
    @Test
    final void testGetComponentsService() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#setClient(SonarQubeClient)}.
     */
    @Test
    final void testSetClient() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#setComponentsService(ComponentsService)}.
     */
    @Test
    final void testSetComponentsService() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#equals(java.lang.Object)}.
     */
    @Test
    final void testEqualsObject() {
	fail("Not yet implemented"); // TODO
    }

    private List<Component> createExpectedComponents(int size, String language) {
	Component prototype = Component.newBuilder().setLanguage(language).build();
	List<Component> result = ComponentSample.getWsComponentList(prototype, size);
	return result;
    }

    private void mockDTOList(List<Component> wsComponents, List<String> qualifiers, String language)
	    throws SearchException {
	SearchWsResponse response = SearchWsResponse.newBuilder().addAllComponents(wsComponents).build();
	String qualifiersParam = qualifiers.stream().collect(Collectors.joining(","));
	Map<String, StringValuePattern> params = new HashMap<>();
	params.put("language", new EqualToPattern(language));
	params.put("qualifiers", new EqualToPattern(qualifiersParam));
	StubMapping mappingSearch = get(urlPathEqualTo(COMPONENTSEARCH_URL)).withQueryParams(params).build();
	StubMapping mappingShow = get(urlPathEqualTo(COMPONENTSHOW_URL))
		.withQueryParam("component", new EqualToPattern(qualifiersParam)).build();
	ResponseDefinitionBuilder builderSearch = ResponseDefinitionBuilder.like(mappingSearch.getResponse())
		.withBody(response.toByteArray());
	ResponseDefinitionBuilder builderShow = ResponseDefinitionBuilder.like(mappingShow.getResponse())
		.withBody(response.toByteArray());
	mappingSearch.setResponse(builderSearch.build());
	mappingShow.setResponse(builderShow.build());
	wmServer.addStubMapping(mappingSearch);
	wmServer.addStubMapping(mappingShow);
    }
}
