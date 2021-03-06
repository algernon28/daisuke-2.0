package com.daisuke.adapters.sonarqube;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.sonarqube.ws.Issues.Issue;
import org.sonarqube.ws.client.issues.SearchRequest;

import com.daisuke.domain.model.IssueDTO;

@Mapper(componentModel = "spring")
public interface IssueMapper {

    @Mapping(target = "additionalFields", source = "additionalFields")
    @Mapping(target = "ascendingSort", source = "asc")
    @Mapping(target = "assigned", source = "assigned")
    @Mapping(target = "assignees", source = "assignees")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "componentKeys", source = "componentKeys")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "createdInLast", source = "createdInLast")
    @Mapping(target = "cwe", source = "cwe")
    @Mapping(target = "directories", source = "directories")
    @Mapping(target = "facets", source = "facets")
    @Mapping(target = "issues", source = "issues")
    @Mapping(target = "languages", source = "languages")
    @Mapping(target = "onComponentOnly", source = "onComponentOnly")
    @Mapping(target = "owaspTop10", source = "owaspTop10")
    @Mapping(target = "page", source = "p")
    @Mapping(target = "pageSize", source = "ps")
    @Mapping(target = "resolutions", source = "resolutions")
    @Mapping(target = "resolved", source = "resolved")
    @Mapping(target = "rules", source = "rules")
    @Mapping(target = "sortField", source = "s")
    @Mapping(target = "sansTop25", source = "sansTop25")
    @Mapping(target = "sinceLeakPeriod", source = "sinceLeakPeriod")
    @Mapping(target = "sonarsourceSecurity", source = "sonarsourceSecurity")
    @Mapping(target = "severities", source = "severities")
    @Mapping(target = "statuses", source = "statuses")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "types", source = "types")
    SearchIssue toSearchIssue(SearchRequest wsRequest);

    @InheritInverseConfiguration(name = "toSearchIssue")
    SearchRequest toWsSearchRequest(SearchIssue search);

    @Mapping(target = "ruleName", ignore = true)
    @Mapping(target = "key", source = "key")
    @Mapping(target = "ruleKey", source = "rule")
    @Mapping(target = "severity", source = "severity")
    @Mapping(target = "line", source = "line")
    @Mapping(target = "message", source = "message")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "component", source = "component")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "resolution", source = "resolution")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "effort", source = "effort")
    @Mapping(target = "assignee", source = "assignee")
    @Mapping(target = "author", source = "author")
    @Mapping(target = "creationDate", source = "creationDate")
    @Mapping(target = "updateDate", source = "updateDate")
    @Mapping(target = "closeDate", source = "closeDate")
    IssueDTO toIssueDTO(Issue wsIssue);

    List<IssueDTO> toIssueDTOList(List<Issue> wsIssues);

    @InheritInverseConfiguration(name = "toIssueDTO")
    Issue toWsIssue(IssueDTO issueDTO);
}
