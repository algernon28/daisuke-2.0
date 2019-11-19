
package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sonarqube.ws.Rules;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.RulesService;
import org.sonarqube.ws.client.rules.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.RuleDTO;
import com.daisuke.domain.model.RuleNotFoundException;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Data
@Slf4j
public class SonarQubeRulesService implements RulesAdapter<SearchRule> {

    private RuleMapper ruleMapper;

    private SonarQubeClient client;
    private RulesService rulesService;
    private static final String RULE_NOT_FOUND = "The search did not return any rule";

    public SonarQubeRulesService(SonarQubeClient client) {
	this.client = client;
	rulesService = client.getRulesService();
    }

    @Autowired
    public void setRuleMapper(RuleMapper mapper) {
	this.ruleMapper = mapper;
    }

    @Override
    public List<RuleDTO> findRules(SearchRule search) throws SearchException {
	client.refreshConnection();
	SearchRequest request = ruleMapper.toWsSearchRequest(search);
	Rules.SearchResponse response = rulesService.search(request);
	Optional<List<Rule>> wsRules = Optional.ofNullable(response.getRulesList());
	List<RuleDTO> result = new ArrayList<>();
	if (wsRules.isPresent() && !wsRules.isEmpty()) {
	    result = ruleMapper.toRuleDTOList(wsRules.get());
	    log.debug("returning list: {}", result);
	} else {
	    log.debug("{}: returning empty list", RULE_NOT_FOUND);
	}
	return result;
    }

    public RuleDTO findRuleByKey(String key) throws SearchException {
	client.refreshConnection();
	SearchRule search = new SearchRule().setRuleKey(key).addFieldToBeReturned("name").setPageSize("1");
	SearchRequest request = ruleMapper.toWsSearchRequest(search);
	Rules.SearchResponse response = null;
	rulesService = client.getRulesService();
	try {
	    response = rulesService.search(request);
	} catch (org.sonarqube.ws.client.HttpException e) {
	    throw new SearchException(e.getMessage(), e);
	}
	Optional<Rule> rule = Optional.ofNullable(response.getRules(0));
	RuleDTO result;
	if (rule.isPresent() && !rule.isEmpty()) {
	    result = ruleMapper.toRuleDTO(rule.get());
	} else {
	    String msg = String.format("%s [key=%s]", RULE_NOT_FOUND, key);
	    throw new RuleNotFoundException(msg);
	}
	log.debug("returning DTO: {}", result);
	return result;
    }
}
