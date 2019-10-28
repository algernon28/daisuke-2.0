package com.daisuke.adapters.sonarqube;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.SearchRequest;

import com.daisuke.domain.model.RuleDTO;

@Mapper
public interface RuleMapper {

    @Mapping(target = "activation", source = "activation")
    @Mapping(target = "activeSeverities", source = "activeSeverities")
    @Mapping(target = "asc", source = "ascendingSort")
    @Mapping(target = "availableSince", source = "availableSince")
    @Mapping(target = "compareToProfile", source = "compareToProfile")
    @Mapping(target = "cwe", source = "cwe")
    @Mapping(target = "f", source = "fieldsToBeReturned")
    @Mapping(target = "facets", source = "facets")
    @Mapping(target = "includeExternal", source = "includeExternal")
    @Mapping(target = "inheritance", source = "inheritance")
    @Mapping(target = "isTemplate", source = "isTemplate")
    @Mapping(target = "languages", source = "languages")
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "owaspTop10", source = "owaspTop10")
    @Mapping(target = "p", source = "page")
    @Mapping(target = "ps", source = "pageSize")
    @Mapping(target = "q", source = "utf8Query")
    @Mapping(target = "qprofile", source = "qprofile")
    @Mapping(target = "repositories", source = "repositories")
    @Mapping(target = "ruleKey", source = "ruleKey")
    @Mapping(target = "s", source = "sortField")
    @Mapping(target = "sansTop25", source = "sansTop25")
    @Mapping(target = "severities", source = "severities")
    @Mapping(target = "sonarsourceSecurity", source = "sonarsourceSecurity")
    @Mapping(target = "statuses", source = "statuses")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "templateKey", source = "templateKey")
    @Mapping(target = "types", source = "types")
    SearchRule toSearchRule(SearchRequest request);

    @InheritInverseConfiguration(name = "toSearchRule")
    SearchRequest toSearchRequest(SearchRule search);

    @Mapping(target = "key", source = "key")
    @Mapping(target = "description", source = "htmlDesc_")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "severity", source = "severity")
    RuleDTO toRuleDTO(Rule rule);

    List<RuleDTO> toRuleDTOList(List<Rule> rules);

    @InheritInverseConfiguration(name = "toRuleDTO")
    Rule toRule(RuleDTO ruleDTO);
}
