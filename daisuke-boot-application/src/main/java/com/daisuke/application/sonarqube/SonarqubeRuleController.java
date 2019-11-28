/**
 * 
 */
package com.daisuke.application.sonarqube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daisuke.adapters.sonarqube.SearchRule;
import com.daisuke.application.ConfigurationData;
import com.daisuke.domain.adapters.RulesAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Andrea M.
 *
 */
@RestController
@RequestMapping("/report")
@Slf4j
public class SonarqubeRuleController {
    private RulesAdapter<SearchRule> rulesAdapter;
    private ConfigurationData config;

    public void setConfiguration(@Autowired ConfigurationData config) {
	this.config = config;
    }

    public void setRulesAdapter(@Autowired RulesAdapter<SearchRule> rulesAdapter) {
	this.rulesAdapter = rulesAdapter;
    }

}
