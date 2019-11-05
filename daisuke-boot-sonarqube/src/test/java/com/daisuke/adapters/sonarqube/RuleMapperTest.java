/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import static com.daisuke.adapters.sonarqube.samples.RuleData.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sonarqube.ws.client.rules.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;

import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

/**
 * @author Andrea M.
 *
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RuleMapperTest {
    @Autowired
    private RuleMapper mapper;
    private SearchRequest wsRequest;

    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    void setUpBeforeClass() throws Exception {
	wsRequest = new SearchRequest().setActivation(randomBOOL()).setActiveSeverities(randomList(2, SeverityEnum.values()))
		.setActivation(randomBOOL()).setAsc(randomBOOL()).setAvailableSince("1999-09-13").setCompareToProfile(randomString(8, false))
		.setCwe(Arrays.asList("rule1","rule2"))
		.setF(Arrays.asList("lang", "tags")).setFacets(Arrays.asList("severities","types", "owaspTop10")).setIncludeExternal(randomBOOL()).setInheritance(Arrays.asList("OVERRIDES"))
		.setIsTemplate("no").setLanguages(Arrays.asList("java","c")).setOrganization("someorganization").setOwaspTop10(Arrays.asList("a1","a4"))
		.setP("1").setPs("500").setQ("xpath").setQprofile("qualityProfileKey-123").setRepositories(Arrays.asList("findbugs","sonarlint"))
		.setRuleKey("rulekey-123").setS("updateAt").setSansTop25(Arrays.asList("risky-resource")).setSeverities(Arrays.asList("BLOCKER","CRITICAL"))
		.setSonarsourceSecurity(Arrays.asList("sql-injection","open-redirect")).setStatuses(Arrays.asList("BETA", "REMOVED")).setTags(Arrays.asList("t1","t2"))
		.setTemplateKey("tk").setTypes(Arrays.asList("BUG"));
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
     * Test method for {@link com.daisuke.adapters.sonarqube.RuleMapper#toSearchRule(org.sonarqube.ws.client.rules.SearchRequest)}.
     */
    @Test
    final void testToSearchRule() {
	SearchRule search = mapper.toSearchRule(wsRequest)
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toWsSearchRequest(com.daisuke.adapters.sonarqube.SearchRule)}.
     */
    @Test
    final void testToWsSearchRequest() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toRuleDTO(org.sonarqube.ws.Rules.Rule)}.
     */
    @Test
    final void testToRuleDTO() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toRuleDTOList(java.util.List)}.
     */
    @Test
    final void testToRuleDTOList() {
	fail("Not yet implemented"); // TODO
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toWsRule(com.daisuke.domain.model.RuleDTO)}.
     */
    @Test
    final void testToWsRule() {
	fail("Not yet implemented"); // TODO
    }

}
