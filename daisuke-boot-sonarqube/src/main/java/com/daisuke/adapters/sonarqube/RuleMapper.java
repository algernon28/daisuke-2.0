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
    @Mapping(target = "ascendingSort", source = "asc")
    @Mapping(target = "availableSince", source = "availableSince")
    @Mapping(target = "compareToProfile", source = "compareToProfile")
    @Mapping(target = "cwe", source = "cwe")
    @Mapping(target = "fieldsToBeReturned", source = "f")
    @Mapping(target = "facets", source = "facets")
    @Mapping(target = "includeExternal", source = "includeExternal")
    @Mapping(target = "inheritance", source = "inheritance")
    @Mapping(target = "isTemplate", source = "isTemplate")
    @Mapping(target = "languages", source = "languages")
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "owaspTop10", source = "owaspTop10")
    @Mapping(target = "page", source = "p")
    @Mapping(target = "pageSize", source = "ps")
    @Mapping(target = "utf8Query", source = "q")
    @Mapping(target = "qprofile", source = "qprofile")
    @Mapping(target = "repositories", source = "repositories")
    @Mapping(target = "ruleKey", source = "ruleKey")
    @Mapping(target = "sortField", source = "s")
    @Mapping(target = "sansTop25", source = "sansTop25")
    @Mapping(target = "severities", source = "severities")
    @Mapping(target = "sonarsourceSecurity", source = "sonarsourceSecurity")
    @Mapping(target = "statuses", source = "statuses")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "templateKey", source = "templateKey")
    @Mapping(target = "types", source = "types")
    SearchRule toSearchRule(SearchRequest wsRequest);

    @InheritInverseConfiguration(name = "toSearchRule")
    SearchRequest toWsSearchRequest(SearchRule search);

    @Mapping(target = "key", source = "key")
    @Mapping(target = "description", source = "htmlDesc")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "severity", source = "severity")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "language.key", source = "lang")
    @Mapping(target = "language.description", source = "langName")
    RuleDTO toRuleDTO(Rule wsRule);

    List<RuleDTO> toRuleDTOList(List<Rule> wsRules);

    @InheritInverseConfiguration(name = "toRuleDTO")
    Rule toWsRule(RuleDTO ruleDTO);
}
