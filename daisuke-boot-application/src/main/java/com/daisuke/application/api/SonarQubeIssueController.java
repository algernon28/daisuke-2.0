package com.daisuke.application.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daisuke.adapters.sonarqube.SearchIssue;
import com.daisuke.domain.adapters.IssuesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.IssueDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Andrea M.
 *
 */
@RestController
@RequestMapping("/report/issues")
@Slf4j
public class SonarQubeIssueController {
    private IssuesAdapter<SearchIssue> issuesAdapter;

    /**
     * Default constructor, the parameter is injected by Spring
     * 
     * @param issuesAdapter The Spring Service retrieving issues
     */
    public SonarQubeIssueController(IssuesAdapter<SearchIssue> issuesAdapter) {
	super();
	this.issuesAdapter = issuesAdapter;
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<IssueDTO>> getIssues(
	    @RequestParam(name = "additionalFields", required = false) List<String> additionalFields,
	    @RequestParam(name = "asc", required = false) String ascendingSort,
	    @RequestParam(name = "assigned", required = false) String assigned,
	    @RequestParam(name = "assignees", required = false) List<String> assignees,
	    @RequestParam(name = "author", required = false) String author,
	    @RequestParam(name = "componentKeys", required = false) List<String> componentKeys,
	    @RequestParam(name = "createdAfter", required = false) String createdAfter,
	    @RequestParam(name = "createdAt", required = false) String createdAt,
	    @RequestParam(name = "createdBefore", required = false) String createdBefore,
	    @RequestParam(name = "createdInLast", required = false) String createdInLast,
	    @RequestParam(name = "cwe", required = false) List<String> cwe,
	    @RequestParam(name = "facets", required = false) List<String> facets,
	    @RequestParam(name = "issues", required = false) List<String> issues,
	    @RequestParam(name = "languages", required = false) List<String> languages,
	    @RequestParam(name = "onComponentOnly", required = false) String onComponentOnly,
	    @RequestParam(name = "owaspTop10", required = false) List<String> owaspTop10,
	    @RequestParam(name = "p", required = false) String page,
	    @RequestParam(name = "ps", required = false) String pageSize,
	    @RequestParam(name = "resolutions", required = false) List<String> resolutions,
	    @RequestParam(name = "resolved", required = false) String resolved,
	    @RequestParam(name = "rules", required = false) List<String> rules,
	    @RequestParam(name = "sansTop25", required = false) List<String> sansTop25,
	    @RequestParam(name = "severities", required = false) List<String> severities,
	    @RequestParam(name = "sonarsourceSecurity", required = false) List<String> sonarsourceSecurity,
	    @RequestParam(name = "statuses", required = false) List<String> statuses,
	    @RequestParam(name = "tags", required = false) List<String> tags,
	    @RequestParam(name = "types", required = false) List<String> types) throws SearchException {
	SearchIssue search = new SearchIssue().setAdditionalFields(additionalFields).setAscendingSort(ascendingSort)
		.setAssigned(assigned).setAssignees(assignees).setAuthor(author).setComponentKeys(componentKeys)
		.setCreatedAfter(createdAfter).setCreatedAt(createdAt).setCreatedInLast(createdInLast)
		.setCreatedBefore(createdBefore).setCwe(cwe).setFacets(facets).setIssues(issues).setLanguages(languages)
		.setOnComponentOnly(onComponentOnly).setOwaspTop10(owaspTop10).setPage(page).setPageSize(pageSize)
		.setResolutions(resolutions).setResolved(resolved).setRules(rules).setSansTop25(sansTop25)
		.setSeverities(severities).setSonarsourceSecurity(sonarsourceSecurity).setStatuses(statuses)
		.setTags(tags).setTypes(types);
	List<IssueDTO> result = issuesAdapter.findIssues(search);
	log.debug("issues found: {}", result);
	return ResponseEntity.ok(result);
    }

}
