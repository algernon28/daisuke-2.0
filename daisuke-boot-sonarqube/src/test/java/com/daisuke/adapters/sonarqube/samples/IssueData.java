package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomDate;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumString;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomNumber;
import static com.daisuke.adapters.sonarqube.Utils.randomSeverity;
import static com.daisuke.adapters.sonarqube.Utils.randomSonarBOOL;
import static com.daisuke.adapters.sonarqube.Utils.randomString;
import static com.daisuke.adapters.sonarqube.Utils.randomStringList;
import static com.daisuke.adapters.sonarqube.Utils.randomType;
import static com.daisuke.adapters.sonarqube.Utils.randomUUIDList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Common.Severity;
import org.sonarqube.ws.Issues.Issue;
import org.sonarqube.ws.Rules.Rule;
import org.sonarqube.ws.client.issues.SearchRequest;

import com.daisuke.adapters.sonarqube.SearchIssue;
import com.daisuke.domain.model.IssueDTO;
import com.daisuke.domain.model.RuleDTO;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

public class IssueData {
    public enum ADDITIONAL_FIELDS_VALUES {
	_all, comments, languages, actionPlans, rules, transitions, actions, users
    }

    public enum FACETS_VALUES {
	PROJECTS("projects"), MODULE_UUIDS("moduleUuids"), FILE_UUIDS("fileUuids"), ASSIGNED_TO_ME("assigned_to_me"),
	SEVERITIES("severities"), STATUSES("statuses"), RESOLUTIONS("resolutions"), RULES("rules"),
	ASSIGNEES("assignees"), AUTHORS("authors"), AUTHOR("author"), DIRECTORY("directories"), LANGUAGES("languages"),
	TAGS("tags"), TYPES("types"), OWASPTOP10("owaspTop10"), SANSTOP25("sansTop25"), CWE("cwe"),
	CREATED_AT("createdAt"), SONARSOURCE_SECURITY("sonarsourceSecurity");

	@SuppressWarnings("unused")
	private String description;

	private FACETS_VALUES(String description) {
	    this.description = description;
	}
    };

    public enum STATUSES_VALUES {
	OPEN, CONFIRMED, REOPENED, RESOLVED, CLOSED, TO_REVIEW, IN_REVIEW, REVIEWED
    }

    public enum INHERITANCE_VALUES {
	NONE, INHERITED, OVERRIDES
    };

    public enum RESOLUTION {
	FALSE_POSITIVE("FALSE-POSITIVE"), WONT_FIX("WONTFIX"), FIXED("FIXED"), REMOVED("REMOVED");
	@SuppressWarnings("unused")
	private String description;

	private RESOLUTION(String description) {
	    this.description = description;
	}
    }

    public static class SearchSample extends CommonDataSample {
	private static List<String> randomAdditionalFields = randomEnumStringList(3, ADDITIONAL_FIELDS_VALUES.values());
	private static String randomAscendingSort = randomSonarBOOL();
	private static String randomAssigned = randomSonarBOOL();
	private static List<String> randomAssignees = randomStringList(2, false);
	private static List<String> randomAuthor = randomStringList(3, true);
	private static List<String> randomComponentKeys = randomStringList(2, true);
	private static String randomCreatedAt = randomDate(2010, 2019);
	private static int month = RandomUtils.nextInt(1, 13);
	private static int weeks = RandomUtils.nextInt(1, 5);
	private static String randomCreatedInLast = String.format("%sm%sw (%s month %s weeks)", month, weeks, month,
		weeks);
	private static List<String> randomCwe = Collections
		.unmodifiableList(Arrays.asList("rule" + randomNumber(1, 100), "rule2" + randomNumber(1, 100)));
	private static List<String> randomDirectories = randomStringList(4, false);
	private static List<String> randomFacets = randomEnumStringList(2, FACETS_VALUES.values());
	private static List<String> randomIssues = randomUUIDList(4);
	private static List<String> randomLanguages = randomEnumStringList(3, LANGUAGES_VALUES.values());
	private static String randomOnComponentOnly = randomSonarBOOL();
	private static List<String> randomOwaspTop10 = randomEnumStringList(3, OWASPTOP10_VALUES.values());
	private static List<String> randomResolutions = randomEnumStringList(3, RESOLUTION.values());
	private static String randomResolved = randomSonarBOOL();
	private static List<String> randomRules = Arrays.asList(String.format("squid:%s", randomString(4, true)));
	private static String randomSortField = randomSonarBOOL();
	private static String randomSinceLeakPeriod = randomSonarBOOL();
	private static List<String> randomSansTop25 = randomEnumStringList(2, SANSTOP25_VALUES.values());
	private static List<String> randomSeverities = randomEnumStringList(2, SeverityEnum.values());
	private static List<String> randomSonarsourceSecurity = randomEnumStringList(2,
		SONARSOURCE_SECURITY_VALUES.values());
	private static List<String> randomStatuses = randomEnumStringList(2, STATUSES_VALUES.values());
	private static List<String> randomTags = Collections.unmodifiableList(Arrays.asList("tag1", "tag2"));
	private static List<String> randomTypes = randomEnumStringList(1, TypeEnum.values());
	private static String randomPage = String.valueOf(randomNumber(1, 10));
	private static String randomPageSize = String.valueOf(randomNumber(1, 500));

	public static SearchRequest getWsSearch() {
	    SearchRequest search = new SearchRequest().setAdditionalFields(randomAdditionalFields)
		    .setAsc(randomAscendingSort).setAssigned(randomAssigned).setAssignees(randomAssignees)
		    .setAuthor(randomAuthor).setComponentKeys(randomComponentKeys)
		    .setCreatedAt(randomCreatedAt).setCreatedInLast(randomCreatedInLast).setCwe(randomCwe)
		    .setDirectories(randomDirectories).setFacets(randomFacets).setIssues(randomIssues)
		    .setLanguages(randomLanguages).setOnComponentOnly(randomOnComponentOnly)
		    .setOwaspTop10(randomOwaspTop10).setP(randomPage).setPs(randomPageSize)
		    .setResolutions(randomResolutions).setResolved(randomResolved).setRules(randomRules)
		    .setS(randomSortField).setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		    .setSinceLeakPeriod(randomSinceLeakPeriod).setSonarsourceSecurity(randomSonarsourceSecurity)
		    .setStatuses(randomStatuses).setTags(randomTags).setTypes(randomTypes);
	    return search;
	}

	public static SearchIssue getSearchIssue() {
	    SearchIssue search = new SearchIssue().setAdditionalFields(randomAdditionalFields)
		    .setAscendingSort(randomAscendingSort).setAssigned(randomAssigned).setAssignees(randomAssignees)
		    .setAuthor(randomAuthor).setComponentKeys(randomComponentKeys)
		    .setCreatedAt(randomCreatedAt).setCreatedInLast(randomCreatedInLast).setCwe(randomCwe)
		    .setDirectories(randomDirectories).setFacets(randomFacets).setIssues(randomIssues)
		    .setLanguages(randomLanguages).setOnComponentOnly(randomOnComponentOnly)
		    .setOwaspTop10(randomOwaspTop10).setPage(randomPage).setPageSize(randomPageSize)
		    .setResolutions(randomResolutions).setResolved(randomResolved).setRules(randomRules)
		    .setSortField(randomSortField).setSansTop25(randomSansTop25).setSeverities(randomSeverities)
		    .setSinceLeakPeriod(randomSinceLeakPeriod).setSonarsourceSecurity(randomSonarsourceSecurity)
		    .setStatuses(randomStatuses).setTags(randomTags).setTypes(randomTypes);
	    return search;
	}

	private SearchSample() {
	}
    }

    public static class IssueSample {
	private static String randomRuleName = String.format("rule-%s", randomString(10, true));
	private static String randomKey = randomString(10, true);
	private static String randomRuleKey = randomString(10, true);
	private static SeverityEnum randomSeverity = randomSeverity();
	private static int randomLine = RandomUtils.nextInt(1, 1000000);
	private static String randomMessage = randomString(100, true);
	private static TypeEnum randomType = randomType();
	private static String randomComponent = randomString(20, true);
	private static String randomProject = randomString(20, true);
	private static String randomResolution = randomEnumString(RESOLUTION.values());
	private static String randomStatus = randomEnumString(STATUSES_VALUES.values());
	private static String randomEffort = String.valueOf(RandomUtils.nextInt(0, 100));
	private static String randomAssignee = randomString(50, true);
	private static String randomAuthor = randomString(50, true);
	private static String randomCreationDate = randomDate(1999, 2019);
	private static String randomUpdateDate = randomDate(1999, 2019);
	private static String randomCloseDate = randomDate(1999, 2000);

	public static Issue getWsIssue() {
	    Issue result = Issue.newBuilder().setAssignee(randomAssignee).setAuthor(randomAuthor)
		    .setCloseDate(randomCloseDate).setComponent(randomComponent).setEffort(randomEffort)
		    .setCreationDate(randomCreationDate).setKey(randomKey).setLine(randomLine).setMessage(randomMessage)
		   .setProject(randomProject).setResolution(randomResolution)
		    .setRule(randomRuleKey).setSeverity(Severity.valueOf(randomSeverity.name())).setStatus(randomStatus)
		    .setType(RuleType.valueOf(randomType.name())).setUpdateDate(randomUpdateDate).build();
	    return result;
	}

	public static IssueDTO getIssueDTO() {
	    IssueDTO result = new IssueDTO().setAssignee(randomAssignee).setAuthor(randomAuthor)
		    .setCloseDate(randomCloseDate).setComponent(randomComponent).setEffort(randomEffort)
		    .setCreationDate(randomCreationDate).setKey(randomKey).setLine(randomLine).setMessage(randomMessage)
		    .setProject(randomProject).setResolution(randomResolution)//.setRuleName(randomRuleName)
		    .setRuleKey(randomRuleKey).setSeverity(randomSeverity)
		    .setStatus(randomStatus).setType(randomType).setUpdateDate(randomUpdateDate);
	    return result;
	}

	public static List<Issue> randomIssueList(int size) {
	    List<Issue> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		Issue issue = getWsIssue();
		result.add(i, issue);
	    }
	    return result;
	}

	public static List<IssueDTO> randomIssueDTOList(int size) {
	    List<IssueDTO> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		IssueDTO issueDTO = getIssueDTO();
		result.add(i, issueDTO);
	    }
	    return result;
	}

	private IssueSample() {
	}
    }
}
