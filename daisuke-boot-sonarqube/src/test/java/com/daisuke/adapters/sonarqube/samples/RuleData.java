package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomDate;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumList;
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

import com.daisuke.domain.model.RuleDTO;
import com.daisuke.domain.model.SeverityEnum;
import com.daisuke.domain.model.TypeEnum;

public abstract class RuleData {

    public enum F_VALUES {
	actives, createdAt, debtOverloaded, debtRemFn, defaultDebtRemFn, defaultRemFn, effortToFixDescription,
	gapDescription, htmlDesc, htmlNote, internalKey, isExternal, isTemplate, lang, langName, mdDesc, mdNote, name,
	noteLogin, params, remFn, remFnOverloaded, repo, scope, severity, status, sysTags, tags, templateKey, updatedAt
    };

    public enum FACETS_VALUES {
	LANGUAGES("languages"), REPOSITORIES("repositories"), TAGS("tags"), SEVERITIES("severities"),
	ACTIVE_SEVERITIES("active_severities"), STATUSES("statuses"), TYPES("types"), TRUE("true"), CWE("cwe"),
	OWASPTOP10("owaspTop10"), SANSTOP25("sansTop25"), SONARSOURCE_SECURITY("sonarsourceSecurity");
	@SuppressWarnings("unused")
	private String description;

	private FACETS_VALUES(String description) {
	    this.description = description;
	}
    };

    public enum INHERITANCE_VALUES {
	NONE, INHERITED, OVERRIDES
    };

    public enum OWASPTOP10_VALUES {
	a1, a2, a3, a4, a5, a6, a7, a8, a9, a10
    };

    public enum LANGUAGES_VALUES {
	ABAP("abap", "ABAP"), APEX("apex", "Apex"), CSHARP("cs", "C#"), CPP("cpp", "C++"), COBOL("cobol", "COBOL"),
	CSS("css", "CSS"), FLEX("flex", "Flex"), GO("go", "Go"), HTML("web", "HTML"), JSP("jsp", "JSP"),
	JAVA("java", "Java"), JAVASCRIPT("js", "JavaScript"), KOTLIN("kotlin", "Kotlin"),
	OBJECTIVEC("objc", "Objective-C"), PHP("php", "PHP"), PLI("pli", "PL/I"), PLSQL("plsql", "PL/SQL"),
	PYTHON("py", "Python"), RPG("rpg", "RPG"), RUBY("ruby", "Ruby"), SCALA("scala", "Scala"),
	SWIFT("swift", "Swift"), TSQL("tsql", "T-SQL"), TYPESCRIPT("ts", "TypeScript"), VBNET("vbnet", "VB.NET"),
	XML("xml", "XML");
	@SuppressWarnings("unused")
	private String key;
	@SuppressWarnings("unused")
	private String description;

	private LANGUAGES_VALUES(String key, String description) {
	    this.key = key;
	    this.description = description;
	}
    }

    public enum S_VALUES {
	name, updatedAt, createdAt, key
    }

    public enum REPOSITORIES_VALUES {
	checkstyle, findbugs, sonarlint, pycharm, stylecop, codacy
    }

    public enum SANSTOP25_VALUES {
	INSECURE_INTERACTION("insecure-interaction"), RISKY_RESOURCE("risky-resource"),
	POROUS_DEFENSE("porous-defense");
	@SuppressWarnings("unused")
	private String description;

	private SANSTOP25_VALUES(String description) {
	    this.description = description;
	}
    }

    public enum SONARSOURCE_SECURITY_VALUES {
	SQL_INJECTION("sql-injection"), COMMAND_INJECTION("command-injection"),
	PATH_TRAVERSAL_INJECTION("path-traversal-injection"), LDAP_INJECTION("ldap-injection"),
	XPATH_INJECTION("xpath-injection"), RCE("rce"), DOS("dos"), SSRF("ssrf"), CSRF("csrf"), XSS("xss"),
	LOG_INJECTION("log-injection"), HTTP_RESPONSE_SPLITTING("http-response-splitting"),
	OPEN_REDIRECT("open-redirect"), XXE("xxe"), OBJECT_INJECTION("object-injection"),
	WEAK_CRYPTOGRAPHY("weak-cryptography"), AUTH("auth"), INSECURE_CONF("insecure-conf"),
	FILE_MANIPOLATION("file-manipulation"), OTHERS("others");
	@SuppressWarnings("unused")
	private String description;

	private SONARSOURCE_SECURITY_VALUES(String description) {
	    this.description = description;
	}
    }

    public enum STATUSES_VALUES {
	BETA, DEPRECATED, READY, REMOVED
    }

    public static final String ACTIVATION = randomSonarBOOL();
    public static final String ACTIVE_SEVERITIES = SeverityEnum.values()[randomNumber(0,
	    SeverityEnum.values().length - 1)].name();
    public static final String ASC = randomSonarBOOL();

    private RuleData() {

    }

    public static class RuleSample {
	public static final String key = randomString(10, true);
	public static final String description = String.format("generic description: %s", randomString(5, true));
	public static final String type = randomEnumString(TypeEnum.values());
	public static final String severity = randomEnumString(SeverityEnum.values());
	public static final Integer occurrencies = randomNumber(0, 1000);

	public static final List<Rule> randomRuleList(int size) {
	    List<Rule> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		Rule rule = Rule.newBuilder().setKey(key + i).setHtmlDesc(description + i)
			.setType(RuleType.valueOf(type)).setSeverity(severity).build();
		result.add(i, rule);
	    }
	    return result;
	}

	public static final List<RuleDTO> randomRuleDTOList(int size) {
	    List<RuleDTO> result = new ArrayList<>();
	    for (int i = 0; i < size; i++) {
		RuleDTO rule = new RuleDTO().setKey(key + i).setDescription(description + i)
			.setType(TypeEnum.valueOf(type)).setSeverity(SeverityEnum.valueOf(severity));
		result.add(i, rule);
	    }
	    return result;
	}

	private RuleSample() {
	}
    }

    public static class SearchSample {
	public static final String randomActivation = randomSonarBOOL();
	public static final List<String> randomActiveSeverities = randomEnumList(2, SeverityEnum.values());
	public static final String randomAsc = randomSonarBOOL();
	public static final String randomCompareToProfile = randomString(8, false);
	public static final String randomDate = randomDate(1999, 2019);
	public static final List<String> randomCwe = Collections
		.unmodifiableList(Arrays.asList("rule" + randomNumber(1, 100), "rule2" + randomNumber(1, 100)));
	public static final List<String> randomFields = randomEnumList(4, F_VALUES.values());
	public static final List<String> randomFacets = randomEnumList(2, FACETS_VALUES.values());
	public static final List<String> randomInheritance = randomEnumList(2, INHERITANCE_VALUES.values());
	public static final String randomIncludeExt = randomSonarBOOL();
	public static final String randomIsTemplate = randomSonarBOOL();
	public static final List<String> randomLanguages = randomEnumList(2, LANGUAGES_VALUES.values());
	public static final String randomOrganization = String.format("org-%s", randomString(15, false));
	public static final List<String> randomOwaspTop10 = randomEnumList(3, OWASPTOP10_VALUES.values());
	public static final String randomPage = String.valueOf(randomNumber(1, 10));
	public static final String randomPageSize = String.valueOf(randomNumber(1, 500));
	public static final String randomUtf8Query = String.format("xpath - %s", randomNumber(1, 1000));
	public static final String randomQProfile = String.format("profile-%s", randomString(5, true));
	public static final List<String> randomRepositories = randomEnumList(2, REPOSITORIES_VALUES.values());
	public static final String randomRuleKey = String.format("squid:%s", randomString(4, true));
	public static final String randomSortField = randomEnumString(S_VALUES.values());
	public static final List<String> randomSansTop25 = randomEnumList(2, SANSTOP25_VALUES.values());
	public static final List<String> randomSeverities = randomEnumList(2, SeverityEnum.values());
	public static final List<String> randomSonarsourceSecurity = randomEnumList(2,
		SONARSOURCE_SECURITY_VALUES.values());
	public static final List<String> randomStatuses = randomEnumList(2, STATUSES_VALUES.values());
	public static final List<String> randomTags = Collections.unmodifiableList(Arrays.asList("tag1", "tag2"));
	public static final String randomTemplateKey = randomString(10, true);
	public static final List<String> randomTypes = randomEnumList(1, TypeEnum.values());

	private SearchSample() {
	}
    }

}
