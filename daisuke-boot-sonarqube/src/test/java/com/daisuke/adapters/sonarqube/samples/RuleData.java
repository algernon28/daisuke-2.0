package com.daisuke.adapters.sonarqube.samples;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import com.daisuke.domain.model.SeverityEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RuleData {

    public static String[] BOOL_VALUES = { "true", "false", "yes", "no" };

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

    public static  final String ACTIVATION = randomBOOL();
    public static final String ACTIVE_SEVERITIES = SeverityEnum.values()[randomNumber(0, SeverityEnum.values().length - 1)]
	    .name();
    public static final String ASC = randomBOOL();
    public static final String AVAILABLE_SINCE = randomDate(new Calendar.Builder().setDate(1971, 1, 1).build().getTime(), new Date());
    public static final List<String> CWE = Arrays.asList("113", "1999", "unknown");
    public static final List <String> FIELDS = randomList(3, F_VALUES.values());
    public static final String ruleKey = String.format("squid:%s", randomString(4, true));

    public static String randomString(int size, boolean isAlphanumeric) {
	String result = (isAlphanumeric) ? RandomStringUtils.randomAlphanumeric(size)
		: RandomStringUtils.randomAlphabetic(size);
	log.debug("random value: {}", result);
	return result;
    }
    
    public static List<String> randomList(int size, @SuppressWarnings("rawtypes") Enum[] values){
	List<String> result = new ArrayList<>();
	for (int i = 1; i <= size; i++) {
	    result.add(values[randomNumber(0, values.length)].name());
	}
	return result;
    }

    public static String randomBOOL() {
	return BOOL_VALUES[randomNumber(0, BOOL_VALUES.length - 1)];
    }

    public static String randomDate(Date startDate, Date endDate) {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
	long start = startDate.getTime();
	long end = endDate.getTime();
	Instant instant = Instant.ofEpochSecond(start + (long) Math.random() * (end - start));
	LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of("UTC-06:00"));
	return result.format(dtf);
    }

    public static int randomNumber(int startInclusive, int endExclusive) {
	int result = RandomUtils.nextInt(startInclusive, endExclusive);
	log.debug("random int: {}", result);
	return result;
    }

    private RuleData() {

    }

}
