package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomDate;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumString;
import static com.daisuke.adapters.sonarqube.Utils.randomNumber;
import static com.daisuke.adapters.sonarqube.Utils.randomSonarBOOL;
import static com.daisuke.adapters.sonarqube.Utils.randomString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.rules.SearchRequest;

import com.daisuke.adapters.sonarqube.SearchRule;
import com.daisuke.domain.model.RuleDTO;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

import enumerations.RuleEnumerations;

public abstract class RuleData implements RuleEnumerations{

    private RuleData() {

    }

    public static class RuleSample {
	private static String key = randomString(10, true);
	private static String description = String.format("generic description: %s", randomString(5, true));
	private static String type = randomEnumString(TypeEnum.values());
	private static String severity = randomEnumString(SeverityEnum.values());
	private static Integer occurrencies = randomNumber(0, 1000);

	public static List<Rule> randomRuleList(int size) {
	    List<Rule> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		Rule rule = getWsRule();
		result.add(i, rule);
	    }
	    return result;
	}

	public static List<RuleDTO> randomRuleDTOList(int size) {
	    List<RuleDTO> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		RuleDTO rule = getRuleDTO();
		result.add(i, rule);
	    }
	    return result;
	}

	public static RuleDTO getRuleDTOComplete() {
	    return getRuleDTO().setOccurrencies(occurrencies);
	}

	public static RuleDTO getRuleDTO() {
	    RuleDTO result = new RuleDTO().setDescription(description).setKey(key)
		    .setSeverity(SeverityEnum.valueOf(severity)).setType(TypeEnum.valueOf(type));
	    return result;
	}

	public static Rule getWsRule() {
	    Rule result = Rule.newBuilder().setHtmlDesc(description).setKey(key).setSeverity(severity)
		    .setType(RuleType.valueOf(type)).build();
	    return result;
	}

	private RuleSample() {
	}
    }

    public static class SearchSample extends CommonDataSample {
	private static String randomActivation = randomSonarBOOL();
	private static List<String> randomActiveSeverities = randomEnumStringList(2, SeverityEnum.values());
	private static String randomAsc = randomSonarBOOL();
	private static String randomCompareToProfile = randomString(8, false);
	private static String randomDate = randomDate(1999, 2019);
	private static List<String> randomCwe = Collections
		.unmodifiableList(Arrays.asList("rule" + randomNumber(1, 100), "rule2" + randomNumber(1, 100)));
	private static List<String> randomFields = randomEnumStringList(4, FIELDS_TO_BE_RETURNED.values());
	private static List<String> randomFacets = randomEnumStringList(2, FACETS.values());
	private static List<String> randomInheritance = randomEnumStringList(2, INHERITANCE.values());
	private static String randomIncludeExt = randomSonarBOOL();
	private static String randomIsTemplate = randomSonarBOOL();
	private static List<String> randomOwaspTop10 = randomEnumStringList(3, OWASPTOP10.values());
	private static String randomUtf8Query = String.format("xpath - %s", randomNumber(1, 1000));
	private static String randomQProfile = String.format("profile-%s", randomString(5, true));
	private static List<String> randomRepositories = randomEnumStringList(2, REPOSITORIES.values());
	private static String randomRuleKey = String.format("squid:%s", randomString(4, true));
	private static String randomSortField = randomEnumString(SORT_FIELD.values());
	private static List<String> randomSansTop25 = randomEnumStringList(2, SANSTOP25.values());
	private static List<String> randomSeverities = randomEnumStringList(2, SeverityEnum.values());
	private static List<String> randomSonarsourceSecurity = randomEnumStringList(2, SONARSOURCE_SECURITY.values());
	private static List<String> randomStatuses = randomEnumStringList(2, STATUSES.values());
	private static List<String> randomTags = Collections.unmodifiableList(Arrays.asList("tag1", "tag2"));
	private static String randomTemplateKey = randomString(10, true);
	private static List<String> randomTypes = randomEnumStringList(1, TypeEnum.values());

	public static SearchRequest getWsSearch() {
	    SearchRequest result = new SearchRequest().setActivation(randomActivation)
		    .setActiveSeverities(randomActiveSeverities).setAsc(randomAsc).setAvailableSince(randomDate)
		    .setCompareToProfile(randomCompareToProfile).setCwe(randomCwe).setF(randomFields)
		    .setFacets(randomFacets).setIncludeExternal(randomIncludeExt).setInheritance(randomInheritance)
		    .setIsTemplate(randomIsTemplate).setLanguages(randomLanguages).setOrganization(randomOrganization)
		    .setOwaspTop10(randomOwaspTop10).setP(randomPage).setPs(randomPageSize).setQ(randomUtf8Query)
		    .setQprofile(randomQProfile).setRepositories(randomRepositories).setRuleKey(randomRuleKey)
		    .setS(randomSortField).setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		    .setSonarsourceSecurity(randomSonarsourceSecurity).setStatuses(randomStatuses).setTags(randomTags)
		    .setTemplateKey(randomTemplateKey).setTypes(randomTypes);
	    return result;
	}

	public static SearchRule getSearchRule() {
	    SearchRule result = new SearchRule().setActivation(randomActivation)
		    .setActiveSeverities(randomActiveSeverities).setAscendingSort(randomAsc)
		    .setAvailableSince(randomDate).setCompareToProfile(randomCompareToProfile).setCwe(randomCwe)
		    .setFieldsToBeReturned(randomFields).setFacets(randomFacets).setIncludeExternal(randomIncludeExt)
		    .setInheritance(randomInheritance).setIsTemplate(randomIsTemplate).setLanguages(randomLanguages)
		    .setOrganization(randomOrganization).setOwaspTop10(randomOwaspTop10).setPage(randomPage)
		    .setPageSize(randomPageSize).setUtf8Query(randomUtf8Query).setQprofile(randomQProfile)
		    .setRepositories(randomRepositories).setRuleKey(randomRuleKey).setSortField(randomSortField)
		    .setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		    .setSonarsourceSecurity(randomSonarsourceSecurity).setStatuses(randomStatuses).setTags(randomTags)
		    .setTemplateKey(randomTemplateKey).setTypes(randomTypes);
	    return result;
	}

	private SearchSample() {
	}
    }

}
