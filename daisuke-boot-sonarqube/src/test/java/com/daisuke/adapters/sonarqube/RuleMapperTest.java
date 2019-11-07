/**
 * 
 */
package com.daisuke.adapters.sonarqube;

import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.description;
import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.key;
import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.randomRuleDTOList;
import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.randomRuleList;
import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.severity;
import static com.daisuke.adapters.sonarqube.samples.RuleData.RuleSample.type;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomActivation;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomAsc;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomCompareToProfile;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomCwe;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomDate;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomFacets;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomFields;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomIncludeExt;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomInheritance;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomIsTemplate;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomLanguages;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomOrganization;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomOwaspTop10;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomPage;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomPageSize;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomQProfile;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomRepositories;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomRuleKey;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomSansTop25;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomSeverities;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomSonarsourceSecurity;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomSortField;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomStatuses;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomTags;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomTemplateKey;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomTypes;
import static com.daisuke.adapters.sonarqube.samples.RuleData.SearchSample.randomUtf8Query;
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
	expectedWsSearch = new SearchRequest().setActivation(randomActivation).setActiveSeverities(randomSeverities)
		.setAsc(randomAsc).setAvailableSince(randomDate).setCompareToProfile(randomCompareToProfile)
		.setCwe(randomCwe).setF(randomFields).setFacets(randomFacets).setIncludeExternal(randomIncludeExt)
		.setInheritance(randomInheritance).setIsTemplate(randomIsTemplate).setLanguages(randomLanguages)
		.setOrganization(randomOrganization).setOwaspTop10(randomOwaspTop10).setP(randomPage)
		.setPs(randomPageSize).setQ(randomUtf8Query).setQprofile(randomQProfile)
		.setRepositories(randomRepositories).setRuleKey(randomRuleKey).setS(randomSortField)
		.setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		.setSonarsourceSecurity(randomSonarsourceSecurity).setStatuses(randomStatuses).setTags(randomTags)
		.setTemplateKey(randomTemplateKey).setTypes(randomTypes);

	expectedSearchRule = new SearchRule().setActivation(randomActivation).setActiveSeverities(randomSeverities)
		.setAscendingSort(randomAsc).setAvailableSince(randomDate).setCompareToProfile(randomCompareToProfile)
		.setCwe(randomCwe).setFieldsToBeReturned(randomFields).setFacets(randomFacets)
		.setIncludeExternal(randomIncludeExt).setInheritance(randomInheritance).setIsTemplate(randomIsTemplate)
		.setLanguages(randomLanguages).setOrganization(randomOrganization).setOwaspTop10(randomOwaspTop10)
		.setPage(randomPage).setPageSize(randomPageSize).setUtf8Query(randomUtf8Query)
		.setQprofile(randomQProfile).setRepositories(randomRepositories).setRuleKey(randomRuleKey)
		.setSortField(randomSortField).setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		.setSonarsourceSecurity(randomSonarsourceSecurity).setStatuses(randomStatuses).setTags(randomTags)
		.setTemplateKey(randomTemplateKey).setTypes(randomTypes);
	expectedRuleDTO = new RuleDTO().setDescription(description).setKey(key)
		.setSeverity(SeverityEnum.valueOf(severity)).setType(TypeEnum.valueOf(type));
	expectedWsRule = Rule.newBuilder().setHtmlDesc(description).setKey(key).setSeverity(severity)
		.setType(RuleType.valueOf(type)).build();
	expectedRuleDTOList = randomRuleDTOList(10);
	expectedRuleList = randomRuleList(10);
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
	assertThat(wsSearch).isEqualToComparingFieldByField(wsSearch);
    }

    /**
     * Test method for
     * {@link com.daisuke.adapters.sonarqube.RuleMapper#toRuleDTO(org.sonarqube.ws.Rules.Rule)}.
     */
    @Test
    final void testToRuleDTO() {
	RuleDTO ruleDTO = mapper.toRuleDTO(expectedWsRule);
	assertThat(expectedRuleDTO).isEqualToComparingFieldByField(ruleDTO);
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
