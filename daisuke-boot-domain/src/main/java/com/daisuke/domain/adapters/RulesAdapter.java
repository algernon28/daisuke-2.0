package com.daisuke.domain.adapters;

import java.util.List;

import com.daisuke.domain.model.RuleDTO;

public interface RulesAdapter<T> {
    List<RuleDTO> findRules(T search) throws SearchException;

    RuleDTO findRuleByKey(String key) throws SearchException; 
    
}
