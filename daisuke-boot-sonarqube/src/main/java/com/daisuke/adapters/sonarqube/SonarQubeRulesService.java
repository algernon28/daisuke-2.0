
package com.daisuke.adapters.sonarqube;

import java.util.List;
import java.util.Optional;

import org.sonarqube.ws.Rules;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.RulesService;
import org.sonarqube.ws.client.rules.SearchRequest;

import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.RuleDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Data
@Slf4j
public class SonarQubeRulesService implements RulesAdapter<SearchRule> {

    private RuleMapper ruleMapper;

    private SonarQubeClient client;
    public static final String RULE_NAME_UNDEFINED = "Rule undefined for key -> %s";

    public SonarQubeRulesService(SonarQubeClient client) {
	this.client = client;
    }

    @Override
    public List<RuleDTO> findRules(SearchRule search) throws SearchException {
        client.refreshConnection();
	SearchRequest request = ruleMapper.toWsSearchRequest(search);
	Rules.SearchResponse response;
	RulesService rulesService = client.getRulesService();
	response = rulesService.search(request);
	Optional<List<Rule>> rules = Optional.ofNullable(response.getRulesList());
	if (rules.isEmpty()) {
	    throw new RuleNotFoundException("No rules found for the criteria");
	}
	List<RuleDTO> ruleDTOList = ruleMapper.toRuleDTOList(response.getRulesList());
	log.debug("returning list: {}", ruleDTOList);
	return ruleDTOList;
    }

    public RuleDTO findRuleByKey(String ruleKey) throws SearchException {
	client.refreshConnection();
	SearchRule search = new SearchRule().setRuleKey(ruleKey).addFieldToBeReturned("name").setPageSize("1");
	SearchRequest request = ruleMapper.toWsSearchRequest(search);
	Rules.SearchResponse response;
	RulesService rulesService = client.getRulesService();
	try {
	    response = rulesService.search(request);
	} catch (org.sonarqube.ws.client.HttpException e) {
	    throw new SearchException(e.getMessage(), e);
	}
	Optional<Rule> rule = Optional.ofNullable(response.getRules(0));
	RuleDTO result;
	if (!rule.isPresent() || rule.isEmpty()) {
	    String message = String.format(RULE_NAME_UNDEFINED, ruleKey);
	    throw new RuleNotFoundException(message);
	} else {
	    result = ruleMapper.toRuleDTO(rule.get());
	}
	log.debug("returning DTO: {}", result);
	return result;
    }
}
