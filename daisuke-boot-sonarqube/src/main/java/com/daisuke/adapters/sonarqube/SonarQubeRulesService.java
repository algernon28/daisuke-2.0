
package com.daisuke.adapters.sonarqube;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sonarqube.ws.Rules;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.RulesService;
import org.sonarqube.ws.client.rules.SearchRequest;
import org.sonarqube.ws.client.rules.ShowRequest;
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
	this.rulesService = client.getRulesService();
    }

    @Autowired
    public void setMapper(RuleMapper mapper) {
	this.ruleMapper = mapper;
    }

    @Override
    public List<RuleDTO> findRules(SearchRule search) throws SearchException {
	client.refreshConnection();
	SearchRequest request = ruleMapper.toWsSearchRequest(search);
	Rules.SearchResponse response = rulesService.search(request);
	Optional<List<Rule>> wsRules = Optional.ofNullable(response.getRulesList());
	List<RuleDTO> result = new ArrayList<>();
	if (wsRules.isPresent()) {
	    result = ruleMapper.toRuleDTOList(wsRules.get());
	    log.debug("returning list: {}", result);
	} else {
	    log.debug("{}: returning empty list", RULE_NOT_FOUND);
	}
	return result;
    }

    @Override
    public RuleDTO findRuleByKey(String key) throws SearchException {

	client.refreshConnection();
	ShowRule showRule = new ShowRule().setKey(key);
	ShowRequest request = ruleMapper.toWsShowRequest(showRule);
	Rules.ShowResponse response = null;
	rulesService = client.getRulesService();
	response = rulesService.show(request);
	Optional<Rule> rule = Optional.ofNullable(response.getRule());
	RuleDTO result;
	if (rule.isPresent()) {
	    result = ruleMapper.toRuleDTO(rule.get());
	} else {
	    String msg = String.format("%s [key=%s]", RULE_NOT_FOUND, key);
	    throw new RuleNotFoundException(msg);
	}
	log.debug("returning DTO: {}", result);
	return result;
    }
}
