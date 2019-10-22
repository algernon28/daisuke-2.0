package com.daisuke.adapters.sonarqube;

import java.util.List;

import com.daisuke.domain.adapters.RulesAdapter;
import com.daisuke.domain.adapters.SearchException;
import com.daisuke.domain.model.RuleDTO;

public class SonarQubeRulesService implements RulesAdapter<SearchRule> {
    private SonarQubeClient client;
    public static final String RULE_NAME_UNDEFINED = "Rule undefined for key -> %s";

    public SonarQubeRulesService(SonarQubeClient client) {
	this.client = client;
    }

    @Override
    public List<RuleDTO> findRules(SearchRule search) throws SearchException {
	// TODO Auto-generated method stub
	return null;
    }

}
