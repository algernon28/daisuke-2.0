package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomDate;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumString;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomMeasure;
import static com.daisuke.adapters.sonarqube.Utils.randomSeverity;
import static com.daisuke.adapters.sonarqube.Utils.randomString;
import static com.daisuke.adapters.sonarqube.Utils.randomType;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.sonarqube.ws.Components.Component;
import org.sonarqube.ws.client.components.SearchRequest;

import com.daisuke.adapters.sonarqube.SearchComponent;
import com.daisuke.domain.model.ComponentDTO;
import com.daisuke.domain.model.ComponentDTO.Measure;
import com.daisuke.domain.model.LanguageEnum;
import com.daisuke.domain.model.MeasureEnum;

import enumerations.ComponentEnumerations;

public abstract class ComponentData implements ComponentEnumerations{



    public static class SearchSample extends CommonDataSample {
	private static List<String> randomQualifiers = randomEnumStringList(2, QUALIFIERS_VALUES.values());
	private static String randomSearchFilter = randomString(15, true);
	private static String randomLanguage = randomLanguages.get(0);

	public static SearchRequest getWsSearch() {
	    SearchRequest result = new SearchRequest().setLanguage(randomLanguage).setOrganization(randomOrganization)
		    .setP(randomPage).setPs(randomPageSize).setQ(randomSearchFilter)
		    .setQualifiers(randomQualifiers);
	    return result;
	}

	public static SearchComponent getSearchComponent() {
	    SearchComponent result = new SearchComponent().setLanguage(randomLanguage)
		    .setOrganization(randomOrganization).setPageSize(randomPageSize).setPage(randomPage)
		    .setSearchFilter(randomSearchFilter).setQualifiers(randomQualifiers);
	    return result;
	}

	private SearchSample() {
	}
    }

    public static class ComponentSample extends CommonDataSample {
	private static String randomKey = randomString(10, true);
	private static String randomRefKey = randomString(10, true);
	private static String randomName = randomString(20, false);
	private static String randomDescription = randomString(50, true);
	private static String randomProjectId = randomString(10, true);
	private static String randomQualifier = randomEnumString(QUALIFIERS_VALUES.values());
	private static String randomPath = randomString(100, false);
	private static String randomAnalysisDate = randomDate(1999, 2019);
	private static String randomLanguage = randomEnumString(LanguageEnum.values());
	private static int randomLinesOfCode = RandomUtils.nextInt(100, 1000000);
	private static BigDecimal randomCQI = BigDecimal.valueOf(RandomUtils.nextDouble(0, 10));
	private static Measure measureValue = new Measure().setEffort(RandomUtils.nextFloat(0, 100))
		.setIssueSeverity(randomSeverity()).setIssueType(randomType())
		.setNumIssues(RandomUtils.nextLong(0, 100));
	private static MeasureEnum measureKey = randomMeasure();

	public static Component getWsComponent() {
	    Component result = Component.newBuilder().setLanguage(randomLanguage).setAnalysisDate(randomAnalysisDate)
		    .setDescription(randomDescription).setKey(randomKey).setName(randomName).setOrganization(randomOrganization)
		    .setProjectId(randomProjectId).setRefKey(randomRefKey).setQualifier(randomQualifier)
		    .setPath(randomPath).build();
	    return result;
	}

	public static ComponentDTO getComponentDTOComplete() {
	    return getComponentDTO().addMeasure(measureKey, measureValue).setCodeQualityIndex(randomCQI)
		    .setLinesOfCode(randomLinesOfCode);
	}

	public static ComponentDTO getComponentDTO() {
	    ComponentDTO result = new ComponentDTO().setAnalysisDate(randomAnalysisDate).setKey(randomKey)
		    .setLanguage(randomLanguage).setName(randomName).setDescription(randomDescription)
		    .setOrganization(randomOrganization).setPath(randomPath).setProjectId(randomProjectId)
		    .setQualifier(randomQualifier).setRefKey(randomRefKey);
	    return result;
	}
    }
}
