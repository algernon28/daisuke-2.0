package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Wrapper around {@code org.sonarqube.ws.client.issues.SearchRequest}, look at
 * official documentation for the meaning and the possible values of the fields.
 * 
 * @author Andrea M.
 * @see <a href=
 *      "https://next.sonarqube.com/sonarqube/web_api/api/issues/search">/api/issues/search</a>
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Validated
public class SearchIssue {
    /**
     * Possible values:
     * <ul>
     * <li>"_all"</li>
     * <li>"comments"</li>
     * <li>"languages"</li>
     * <li>"actionPlans"</li>
     * <li>"rules"</li>
     * <li>"transitions"</li>
     * <li>"actions"</li>
     * <li>"users"</li>
     * </ul>
     */
    private List<String> additionalFields;
    /**
     * Possible values: true/false/yes/no
     */
    private String ascendingSort;
    /**
     * Possible values: true/false/yes/no
     */
    private String assigned;
    /**
     * Example value: "admin,usera,__me__"
     */
    private List<String> assignees;
    /**
     * Example value:
     * "author=torvalds@linux-foundation.org&author=linux@fondation.org"
     */

    private List<String> author;
    /**
     * Example value: "feature/my_branch"
     */
    private String branch;
    /**
     * Example value: "my_project"
     */
    private List<String> componentKeys;
    /**
     * Example value: "2017-10-19 or 2017-10-19T13:00:00+0200"
     */
    private String createdAt;
    /**
     * Example value: "2017-10-19 or 2017-10-19T13:00:00+0200"
     */
    private String createdInLast;
    /**
     * Example value: "12,125,unknown"
     */
    private List<String> cwe;
    /**
     * Example value: "src/main/java/org/sonar/server/"
     */
    private List<String> directories;
    /**
     * Possible values:
     * <ul>
     * <li>"count"</li>
     * <li>"effort"</li>
     * </ul>
     */
    private String facetMode;

    /**
     * Possible values:
     * <ul>
     * <li>"projects"</li>
     * <li>"moduleUuids"</li>
     * <li>"fileUuids"</li>
     * <li>"assigned_to_me"</li>
     * <li>"severities"</li>
     * <li>"statuses"</li>
     * <li>"resolutions"</li>
     * <li>"actionPlans"</li>
     * <li>"rules"</li>
     * <li>"assignees"</li>
     * <li>"reporters"</li>
     * <li>"authors"</li>
     * <li>"author"</li>
     * <li>"directories"</li>
     * <li>"languages"</li>
     * <li>"tags"</li>
     * <li>"types"</li>
     * <li>"owaspTop10"</li>
     * <li>"sansTop25"</li>
     * <li>"cwe"</li>
     * <li>"createdAt"</li>
     * <li>"sonarsourceSecurity"</li>
     * </ul>
     */
    private List<String> facets;
    /**
     * Example value: "bdd82933-3070-4903-9188-7d8749e8bb92"
     */
    private List<String> fileUuids;
    /**
     * Example value: "5bccd6e8-f525-43a2-8d76-fcb13dde79ef"
     */
    private List<String> issues;
    /**
     * Example value: "java,js"
     */
    private List<String> languages;
    /**
     * Example value: "my-org"
     */
    private String organization;
    /**
     * Possible values:
     * <ul>
     * <li>"a1"</li>
     * <li>"a2"</li>
     * <li>"a3"</li>
     * <li>"a4"</li>
     * <li>"a5"</li>
     * <li>"a6"</li>
     * <li>"a7"</li>
     * <li>"a8"</li>
     * <li>"a9"</li>
     * <li>"a10"</li>
     * <li>"unknown"</li>
     * </ul>
     */
    private List<String> owaspTop10;
    /**
     * Example value: "42"
     */
    private String page;
    /**
     * Example value: "my_project"
     */
    private List<String> projects;
    /**
     * Number of items allowed for each page. Max items = 500<br/>
     * 
     * Example value: "20"
     */
    private String pageSize;
    /**
     * Example value: "5461"
     */
    private String pullRequest;
    /**
     * Example value: "FIXED,REMOVED" Possible values:
     * <ul>
     * <li>"FALSE-POSITIVE"</li>
     * <li>"WONTFIX"</li>
     * <li>"FIXED"</li>
     * <li>"REMOVED"</li>
     * </ul>
     */
    private List<String> resolutions;
    /**
     * Possible values: true/false/yes/no
     */
    private String resolved;
    /**
     * Example value: "squid:AvoidCycles"
     */
    private List<String> rules;
    /**
     * Possible values:
     * <ul>
     * <li>"insecure-interaction"</li>
     * <li>"risky-resource"</li>
     * <li>"porous-defenses"</li>
     * </ul>
     */
    private List<String> sansTop25;
    /**
     * Example value: "BLOCKER,CRITICAL" Possible values:
     * <ul>
     * <li>"INFO"</li>
     * <li>"MINOR"</li>
     * <li>"MAJOR"</li>
     * <li>"CRITICAL"</li>
     * <li>"BLOCKER"</li>
     * </ul>
     */
    private List<String> severities;
    /**
     * Example value: "OPEN,REOPENED" Possible values:
     * <ul>
     * <li>"OPEN"</li>
     * <li>"CONFIRMED"</li>
     * <li>"REOPENED"</li>
     * <li>"RESOLVED"</li>
     * <li>"CLOSED"</li>
     * </ul>
     */
    private List<String> statuses;
    /**
     * Example value: "security,convention"
     */
    private List<String> tags;
    /**
     * Example value: "CODE_SMELL,BUG" Possible values:
     * <ul>
     * <li>"CODE_SMELL"</li>
     * <li>"BUG"</li>
     * <li>"VULNERABILITY"</li>
     * <li>"SECURITY_HOTSPOT"</li>
     * </ul>
     */
    private List<String> types;

    public SearchIssue addComponentKey(String componentKey) {
	if (componentKey == null) {
	    return this;
	}
	if (componentKeys == null) {
	    componentKeys = new ArrayList<>();
	}
	componentKeys.add(componentKey);
	return this;
    }

    public SearchIssue addSeverity(SeverityEnum severity) {
	if (severity == null) {
	    return this;
	}
	if (severities == null) {
	    severities = new ArrayList<>();
	}
	severities.add(severity.name());
	return this;
    }

    public SearchIssue addType(TypeEnum type) {
	if (type == null) {
	    return this;
	}
	if (types == null) {
	    types = new ArrayList<>();
	}
	types.add(type.name());
	return this;
    }
}
