package com.daisuke.adapters.sonarqube;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.client.components.SearchRequest;
import org.sonarqube.ws.client.components.ShowRequest;

import com.daisuke.domain.model.ComponentDTO;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

    @Mapping(target = "language", source = "language")
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "pageSize", source = "ps")
    @Mapping(target = "page", source = "p")
    @Mapping(target = "qualifiers", source = "qualifiers")
    @Mapping(target = "searchFilter", source = "q")
    SearchComponent toSearchComponent(SearchRequest wsRequest);

    @InheritInverseConfiguration(name = "toSearchComponent")
    SearchRequest toWsSearchRequest(SearchComponent search); 

    @Mapping(target = "componentKey", source = "component")
    ShowComponent toShowComponent(ShowRequest wsRequest);

    @InheritInverseConfiguration(name = "toShowComponent")
    ShowRequest toWsShowRequest(ShowComponent search); 

    @Mapping(target = "key", source = "key")
    @Mapping(target = "refKey", source = "refKey")
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "projectId", source = "projectId")
    @Mapping(target = "qualifier", source = "qualifier")
    @Mapping(target = "path", source = "path")
    @Mapping(target = "analysisDate", source = "analysisDate")
    @Mapping(target = "language", source = "language")
    ComponentDTO toComponentDTO(Component wsComponent);

    @InheritInverseConfiguration(name = "toComponentDTO") 
    Component toWsComponent(ComponentDTO componentDTO);

    List<ComponentDTO> toComponentDTOList(List<Component> wsComponents);
}
