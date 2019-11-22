package com.daisuke.domain.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.daisuke.domain.model.RuleDTO;

@Component
public interface RulesAdapter<T> {
    List<RuleDTO> findRules(T search) throws SearchException;

    RuleDTO findRuleByKey(String key) throws SearchException; 
    
}
