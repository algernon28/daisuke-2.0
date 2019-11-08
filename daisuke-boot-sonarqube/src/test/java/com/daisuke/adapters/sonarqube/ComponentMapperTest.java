/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.samples.ComponentData.ComponentSample.*;
import static com.daisuke.adapters.sonarqube.samples.ComponentData.SearchSample.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.client.components.SearchRequest;
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
    private Component expectedWsComponent;
    private ComponentDTO expectedComponentDTO;
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	mapper = Mappers.getMapper(ComponentMapper.class);
	expectedWsSearch = getWsSearch();
	expectedSearchComponent = getSearchComponent();
	expectedWsComponent = getWsComponent();
	expectedComponentDTO = getComponentDTO();
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toSearchComponent(org.sonarqube.ws.client.components.SearchRequest)}.
     */
    @Test
    final void testToSearchComponent() {
	SearchComponent search = mapper.toSearchComponent(expectedWsSearch);
	assertThat(expectedSearchComponent).isEqualToComparingFieldByField(search);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toWsSearchRequest(com.daisuke.adapters.sonarqube.SearchComponent)}.
     */
    @Test
    final void testToWsSearchRequest() {
	SearchRequest wsSearch = mapper.toWsSearchRequest(expectedSearchComponent);
	assertThat(expectedWsSearch).isEqualToComparingFieldByField(wsSearch);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toComponentDTO(org.sonarqube.ws.Components.Component)}.
     */
    @Test
    final void testToComponentDTO() {
	ComponentDTO componentDTO = mapper.toComponentDTO(expectedWsComponent);
	assertThat(expectedComponentDTO).isEqualToComparingFieldByField(componentDTO);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.ComponentMapper#toWsComponent(com.daisuke.domain.model.ComponentDTO)}.
     */
    @Test
    final void testToWsComponent() {
	Component wsComponent = mapper.toWsComponent(expectedComponentDTO);
	assertThat(expectedWsComponent).isEqualToComparingFieldByField(wsComponent);
    }

}
