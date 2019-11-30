/**
 * 
 */
package com.daisuke.controllers.sonarqube;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.daisuke.adapters.sonarqube.SearchRule;
import com.daisuke.adapters.sonarqube.SonarQubeRulesService;
import com.daisuke.adapters.sonarqube.config.SonarQubeConfiguration;
import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.RuleDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Andrea M.
 *
 */
@RestController
@RequestMapping("/report")
@Slf4j
public class RuleController {
    private RulesAdapter<SearchRule> rulesAdapter;
    private SonarQubeConfiguration config;

    @Autowired
    void setConfiguration(SonarQubeConfiguration config) {
	this.config = config;
    }

    @Autowired
    void setRuleAdapter(RulesAdapter<SearchRule> adapter) {
	this.rulesAdapter = adapter;
    }

    @GetMapping("/rules/search")
    @ResponseBody
    public ResponseEntity<List<RuleDTO>> getRules(
	    @RequestParam(name = "activation", required = false) String activation,
	    @RequestParam(name = "active_severities", required = false) List<String> activeSeverities,
	    @RequestParam(name = "asc", required = false) String ascendingSort,
	    @RequestParam(name = "available_since", required = false) String availableSince,
	    @RequestParam(name = "cwe", required = false) List<String> cwe,
	    @RequestParam(name = "f", required = false) List<String> fieldsToBeReturned,
	    @RequestParam(name = "facets", required = false) List<String> facets,
	    @RequestParam(name = "include_external", required = false) String includeExternal,
	    @RequestParam(name = "inheritance", required = false) List<String> inheritance,
	    @RequestParam(name = "is_template", required = false) String isTemplate,
	    @RequestParam(name = "languages", required = false) List<String> languages,
	    @RequestParam(name = "owaspTop10", required = false) List<String> owaspTop10,
	    @RequestParam(name = "p", required = false) String page,
	    @RequestParam(name = "ps", required = false) String pageSize,
	    @RequestParam(name = "q", required = false) String utf8Query,
	    @RequestParam(name = "qprofile", required = false) String qprofile,
	    @RequestParam(name = "repositories", required = false) List<String> repositories,
	    @RequestParam(name = "rule_key", required = false) String ruleKey,
	    @RequestParam(name = "s", required = false) String sortField,
	    @RequestParam(name = "sansTop25", required = false) List<String> sansTop25,
	    @RequestParam(name = "severities", required = false) List<String> severities,
	    @RequestParam(name = "sonarsourceSecurity", required = false) List<String> sonarsourceSecurity,
	    @RequestParam(name = "statuses", required = false) List<String> statuses,
	    @RequestParam(name = "tags", required = false) List<String> tags,
	    @RequestParam(name = "template_key", required = false) String templateKey,
	    @RequestParam(name = "types", required = false) List<String> types) throws SearchException {
	SearchRule search = new SearchRule().setActivation(activation).setActiveSeverities(activeSeverities)
		.setAscendingSort(ascendingSort).setAvailableSince(availableSince).setCwe(cwe)
		.setFieldsToBeReturned(fieldsToBeReturned).setFacets(facets).setIncludeExternal(includeExternal)
		.setInheritance(inheritance).setIsTemplate(isTemplate).setLanguages(languages).setOwaspTop10(owaspTop10)
		.setPage(page).setPageSize(pageSize).setUtf8Query(utf8Query).setQprofile(qprofile)
		.setRepositories(repositories).setRuleKey(ruleKey).setSortField(sortField).setSansTop25(sansTop25)
		.setSeverities(severities).setSonarsourceSecurity(sonarsourceSecurity).setStatuses(statuses)
		.setTags(tags).setTemplateKey(templateKey).setTypes(types);
	List<RuleDTO> result = rulesAdapter.findRules(search);
	log.debug("rules found: {}", result);
	return ResponseEntity.ok(result                      );
    }

}
