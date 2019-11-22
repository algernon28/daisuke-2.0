package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sonarqube.ws.Components;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.client.components.ComponentsService;
import org.sonarqube.ws.client.components.SearchRequest;
import org.sonarqube.ws.client.components.ShowRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.daisuke.domain.adapters.ComponentsAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.ComponentDTO;
import com.daisuke.domain.model.ComponentNotFoundException;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Data
@Slf4j
public class SonarQubeComponentService implements ComponentsAdapter<SearchComponent> {
    private ComponentMapper componentMapper;
    private SonarQubeClient client;
    private ComponentsService componentsService;
    private static final String COMPONENT_NOT_FOUND = "The search did not return any component";

    public SonarQubeComponentService(SonarQubeClient client) {
	this.client = client;
	this.componentsService = client.getComponentsService();
    }

    @Autowired
    public void setMapper(ComponentMapper mapper) {
	this.componentMapper = mapper;
    }

    @Override
    public List<ComponentDTO> findComponents(SearchComponent search) throws SearchException {
	client.refreshConnection();
	SearchRequest request = componentMapper.toWsSearchRequest(search);
	Components.SearchWsResponse response = componentsService.search(request);
	Optional<List<Component>> wsComponents = Optional.ofNullable(response.getComponentsList());
	List<ComponentDTO> result = new ArrayList<>();
	if (wsComponents.isPresent() && !wsComponents.isEmpty()) {
	    result = componentMapper.toComponentDTOList(wsComponents.get());
	    log.debug("returning list: {}", result);
	} else {
	    log.debug("{}, returning empty list", COMPONENT_NOT_FOUND);
	}
	return result;
    }

    @Override
    public ComponentDTO findComponentByKey(String key) throws SearchException {
	client.refreshConnection();
	ShowComponent showComponent = new ShowComponent().setComponentKey(key);
	ShowRequest request = componentMapper.toWsShowRequest(showComponent);
	Components.ShowWsResponse response = componentsService.show(request);
	Optional<Component> wsComponent = Optional.ofNullable(response.getComponent());
	ComponentDTO result = null;
	if (wsComponent.isPresent() && !wsComponent.isEmpty()) {
	    result = componentMapper.toComponentDTO(wsComponent.get());
	} else {
	    String msg = String.format("%s [key=%s]", COMPONENT_NOT_FOUND, key);
	    throw new ComponentNotFoundException(msg);
	}
	return result;
    }

}
