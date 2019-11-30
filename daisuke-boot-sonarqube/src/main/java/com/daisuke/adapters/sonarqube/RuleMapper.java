package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.Rules.Tags;
import org.sonarqube.ws.Rules.TagsOrBuilder;
import org.sonarqube.ws.client.rules.SearchRequest;

import com.daisuke.domain.model.RuleDTO;

@Mapper(componentModel = "spring")
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
    @Mapping(target = "templateKey", source = "templateKey")
    @Mapping(target = "types", source = "types")
    @Mapping(target = "tags", source = "tags")
    SearchRule toSearchRule(SearchRequest wsRequest);

    @InheritInverseConfiguration(name = "toSearchRule")
    SearchRequest toWsSearchRequest(SearchRule search);

    @Mapping(target = "key", source = "key")
    @Mapping(target = "description", source = "htmlDesc")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "severity", source = "severity")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "language", source = "lang")
    @Mapping(target = "occurrencies", ignore = true)
    @Mapping(target = "tags", source = "tags")
    RuleDTO toRuleDTO(Rule wsRule);

    List<RuleDTO> toRuleDTOList(List<Rule> wsRules);

    @InheritInverseConfiguration(name = "toRuleDTO")
    @Mapping(target = "langName", source = "language.description")
    @Mapping(target = "lang", source = "language")
    Rule toWsRule(RuleDTO ruleDTO);

    default List<String> toTagsString(TagsOrBuilder tags) {
	List<String> result = new ArrayList<>();
	result.addAll(tags.getTagsList());
	return result;
    }

    default Tags toTags(List<String> stringList) {
	return Tags.newBuilder().addAllTags(stringList).build();
    }

}
