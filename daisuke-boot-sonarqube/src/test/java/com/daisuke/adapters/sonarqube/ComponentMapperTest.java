/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.samples.ComponentData.ComponentSample.*;
import static com.daisuke.adapters.sonarqube.samples.ComponentData.SearchSample.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.client.components.SearchRequest;
import static com.daisuke.adapters.sonarqube.Utils.*;
import com.daisuke.domain.model.ComponentDTO;

/**
 * @author Andrea M.
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ComponentMapperTest {

    private ComponentMapper mapper;
    private SearchRequest expectedWsSearch;
    private SearchComponent expectedSearchComponent;
    private Component wsComponent;
    private ComponentDTO componentDTO;
    private List<Component> expectedComponentList;
    private List<ComponentDTO> expectedComponentDTOList;

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	mapper = Mappers.getMapper(ComponentMapper.class);
	expectedWsSearch = new SearchRequest().setLanguage(randomLanguage).setOrganization(randomOrganization)
		.setP(randomPage.toString()).setPs(randomPageSize.toString()).setQ(randomSearchFilter)
		.setQualifiers(randomQualifiers);
	expectedSearchComponent = new SearchComponent().setLanguage(randomLanguage).setOrganization(randomOrganization)
		.setPageSize(randomPageSize.toString()).setPage(randomPage.toString())
		.setSearchFilter(randomSearchFilter).setQualifiers(randomQualifiers);
	wsComponent = Component.newBuilder().setLanguage(randomLanguage).setAnalysisDate(randomDate(2010, 2019))
		.setDescription(randomString(50, true)).setKey(randomString(5, true)).setLanguage(randomLanguage)
		.set
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toSearchComponent(org.sonarqube.ws.client.components.SearchRequest)}.
     */
    @Test
    final void testToSearchComponent() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toWsSearchRequest(com.daisuke.adapters.sonarqube.SearchComponent)}.
     */
    @Test
    final void testToWsSearchRequest() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toComponentDTO(org.sonarqube.ws.Components.Component)}.
     */
    @Test
    final void testToComponentDTO() {
	fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toWsComponent(com.daisuke.domain.model.ComponentDTO)}.
     */
    @Test
    final void testToWsComponent() {
	fail("Not yet implemented");
    }

}
