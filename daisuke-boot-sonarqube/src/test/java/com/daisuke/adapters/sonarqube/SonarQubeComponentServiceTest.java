/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.Constants.COMPONENTSEARCH_URL;
import static com.daisuke.adapters.sonarqube.Constants.COMPONENTSHOW_URL;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.Components.SearchWsResponse;

import com.daisuke.adapters.sonarqube.samples.ComponentData.ComponentSample;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.ComponentDTO;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.matching.EqualToPattern;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

/**
 * @author Andrea M.
 *
 */
@TestInstance(Lifecycle.PER_CLASS)
class SonarQubeComponentServiceTest  extends AbstractWireMockTest<SonarQubeComponentService, ComponentMapper>{

    
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	super.init(SonarQubeComponentService.class, ComponentMapper.class);
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
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#findComponents(com.daisuke.adapters.sonarqube.SearchComponent)}.
     * 
     * @throws SearchException
     */
    @Test
    final void testFindComponents() throws SearchException {
	int resultSize = 20;
	String language = "java";
	// create golden data
	List<Component> wsComponents = expectedComponentList(resultSize, language);
	List<ComponentDTO> expected = mapper.toComponentDTOList(wsComponents);
	List<String> qualifiers = wsComponents.stream().flatMap(comp -> Stream.of(comp.getQualifier())).distinct()
		.collect(Collectors.toList());
	SearchComponent search = new SearchComponent().setLanguage(language).setQualifiers(qualifiers);
	// execute tested method to find actual data
	mockDTOList(wsComponents, qualifiers, language);
	List<ComponentDTO> actual = service.findComponents(search);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#findComponentByKey(java.lang.String)}.
     * 
     * @throws SearchException
     */
    @Test
    final void testFindComponentByKey() throws SearchException {
	String key = Utils.randomString(10, true);
	ComponentDTO expected = expectedComponentDTO(key);
	Component cmp = mapper.toWsComponent(expected);
	mockDTO(cmp, key);
	ComponentDTO actual = service.findComponentByKey(key);
	assertThat(expected).isEqualTo(actual);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.SonarQubeComponentService#SonarQubeComponentService()}.
     */
    @Test
    final void testSonarQubeComponentService() {
	SonarQubeComponentService service = new SonarQubeComponentService();
	assertThat(service).isNotNull();
    }

    private List<Component> expectedComponentList(int size, String language) {
	Component prototype = Component.newBuilder().setLanguage(language).build();
	List<Component> result = ComponentSample.getWsComponentList(prototype, size);
	return result;
    }

    private ComponentDTO expectedComponentDTO(String key) {
	ComponentDTO result = ComponentSample.getComponentDTO();
	result = result.setKey(key);
	return result;
    }

    private void mockDTO(Component wsComponent, String key) {
	SearchWsResponse response = SearchWsResponse.newBuilder().addComponents(wsComponent).build();
	StubMapping mapping = get(urlPathEqualTo(COMPONENTSHOW_URL))
		.withQueryParam("component", new EqualToPattern(key)).build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
    }

    private void mockDTOList(List<Component> wsComponents, List<String> qualifiers, String language)
	    throws SearchException {
	SearchWsResponse response = SearchWsResponse.newBuilder().addAllComponents(wsComponents).build();
	String qualifiersParam = qualifiers.stream().collect(Collectors.joining(","));
	Map<String, StringValuePattern> params = new HashMap<>();
	params.put("language", new EqualToPattern(language));
	params.put("qualifiers", new EqualToPattern(qualifiersParam));
	StubMapping mapping = get(urlPathEqualTo(COMPONENTSEARCH_URL)).withQueryParams(params).build();
	ResponseDefinitionBuilder builder = ResponseDefinitionBuilder.like(mapping.getResponse())
		.withBody(response.toByteArray());
	mapping.setResponse(builder.build());
	wmServer.addStubMapping(mapping);
    }
}
