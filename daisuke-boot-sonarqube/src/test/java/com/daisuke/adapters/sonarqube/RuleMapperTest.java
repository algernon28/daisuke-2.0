/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.*;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.SearchRequest;

import com.daisuke.domain.model.RuleDTO;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

/**
 * @author Andrea M.
 *
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RuleMapperTest {

    private RuleMapper mapper;
    private SearchRequest expectedWsSearch;
    private SearchRule expectedSearchRule;
    private Rule expectedWsRule;
    private RuleDTO expectedRuleDTO;
    private List<Rule> expectedRuleList;
    private List<RuleDTO> expectedRuleDTOList;

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	mapper = Mappers.getMapper(RuleMapper.class);
	expectedWsSearch = getWsSearch();
	expectedSearchRule = getSearchRule();
	expectedRuleDTO =getRuleDTO();
	expectedRuleDTOList = randomRuleDTOList(10);
	expectedRuleList = randomRuleList(10);
	expectedWsRule = getWsRule();
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterEach
    void tearDown() throws Exception {

    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toSearchRule(org.sonarqube.ws.client.rules.SearchRequest)}.
     */
    @Test
    final void testToSearchRule() {
	SearchRule search = mapper.toSearchRule(expectedWsSearch);
	assertThat(expectedSearchRule).isEqualToComparingFieldByField(search);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toWsSearchRequest(com.daisuke.adapters.sonarqube.SearchRule)}.
     */
    @Test
    final void testToWsSearchRequest() {
	SearchRequest wsSearch = mapper.toWsSearchRequest(expectedSearchRule);
	assertThat(expectedWsSearch).isEqualToComparingFieldByField(wsSearch);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toRuleDTO(org.sonarqube.ws.Rules.Rule)}.
     */
    @Test
    final void testToRuleDTO() {
	RuleDTO ruleDTO = mapper.toRuleDTO(expectedWsRule);
	assertThat(expectedRuleDTO).isEqualTo(ruleDTO);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toRuleDTOList(java.util.List)}.
     */
    @Test
    final void testToRuleDTOList() {
	List<RuleDTO> list = mapper.toRuleDTOList(expectedRuleList);
	assertThat(expectedRuleDTOList).isEqualTo(list);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toWsRule(com.daisuke.domain.model.RuleDTO)}.
     */
    @Test
    final void testToWsRule() {
	Rule rule = mapper.toWsRule(expectedRuleDTO);
	assertThat(expectedWsRule).isEqualToComparingFieldByField(rule);
    }

}
