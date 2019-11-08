package com.daisuke.adapters.sonarqube.samples;

import com.daisuke.domain.model.IssueDTO;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;
import static com.daisuke.adapters.sonarqube.Utils.*;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.apache.commons.lang3.RandomUtils;
import org.sonarqube.ws.Common.RuleType;
import org.sonarqube.ws.Common.Severity;
import org.sonarqube.ws.Issues.ActionPlan;
import org.sonarqube.ws.Issues.Issue;

public class IssueData {
    public enum RESOLUTION {
	FALSE_POSITIVE("FALSE-POSITIVE"), WONT_FIX("WONTFIX"), FIXED("FIXED"), REMOVED("REMOVED");
	@SuppressWarnings("unused")
	private String description;

	private RESOLUTION(String description) {
	    this.description = description;
	}
    }

    public enum STATUSES_VALUES {
	OPEN, CONFIRMED, REOPENED, RESOLVED, CLOSED, TO_REVIEW, IN_REVIEW, REVIEWED
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
	private static String randomOrganization = randomString(10, true);
	private static String randomCloseDate = randomDate(1999, 2000);

	private String actionPlan = randomString(10, true);

	public static Issue getWsIssue() {
	    Issue result = Issue.newBuilder().setAssignee(randomAssignee).setAuthor(randomAuthor)
		    .setCloseDate(randomCloseDate).setComponent(randomComponent).setEffort(randomEffort)
		    .setCreationDate(randomCreationDate).setKey(randomKey).setLine(randomLine).setMessage(randomMessage)
		    .setOrganization(randomOrganization).setProject(randomProject).setResolution(randomResolution)
		    .setRule(randomRuleKey).setSeverity(Severity.valueOf(randomSeverity.name())).setStatus(randomStatus)
		    .setType(RuleType.valueOf(randomType.name())).setUpdateDate(randomUpdateDate).build();
	    return result;
	}

	public static IssueDTO getIssueDTO() {
	    IssueDTO result = new IssueDTO().setAssignee(randomAssignee).setAuthor(randomAuthor)
		    .setCloseDate(randomCloseDate).setComponent(randomComponent).setEffort(randomEffort)
		    .setCreationDate(randomCreationDate).setKey(randomKey).setLine(randomLine).setMessage(randomMessage)
		    .setOrganization(randomOrganization).setProject(randomProject).setResolution(randomResolution)
		    .setRuleKey(randomRuleKey).setRuleName(randomRuleName).setSeverity(randomSeverity)
		    .setStatus(randomStatus).setType(randomType).setUpdateDate(randomUpdateDate);
	    return result;
	}
    }
}
