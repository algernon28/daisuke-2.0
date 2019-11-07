package com.daisuke.adapters.sonarqube.samples;

import static com.daisuke.adapters.sonarqube.Utils.randomDate;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumList;
import static com.daisuke.adapters.sonarqube.Utils.randomEnumString;
import static com.daisuke.adapters.sonarqube.Utils.randomString;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.daisuke.domain.model.ComponentDTO.Measure;
import com.daisuke.domain.model.MeasureEnum;

public abstract class ComponentData {
    public enum LANGUAGE_VALUES {
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

	private LANGUAGE_VALUES(String key, String description) {
	    this.key = key;
	    this.description = description;
	}
    }

    public enum QUALIFIERS_VALUES {
	APP("Applications"), BRC("Sub-projects"), DIR("Directories"), FIL("Files"), SVW("Portfolios"), TRK("Projects"),
	UTS("Test Files"), VW("Portfolios");

	@SuppressWarnings("unused")
	private String description;

	private QUALIFIERS_VALUES(String description) {
	    this.description = description;
	}
    }

    public static class SearchSample extends CommonDataSample {
	public static String randomLanguage = randomEnumString(LANGUAGE_VALUES.values());
	public static List<String> randomQualifiers = randomEnumList(2, QUALIFIERS_VALUES.values());
	public static String randomSearchFilter = randomString(15, true);

	private SearchSample() {
	}
    }

    public static class ComponentSample extends CommonDataSample {
	public static String key = randomString(10, true);
	public static String refKey = randomString(10, true);
	public static String name = randomString(20, false);
	public static String description = randomString(50, true);
	public static String projectId = randomString(10, true);
	public static String qualifier = randomEnumString(QUALIFIERS_VALUES.values());
	public static String path = randomString(100, false);
	public static String analysisDate = randomDate(1999, 2019);
	public static String language = randomEnumString(LANGUAGE_VALUES.values());
	public static int linesOfCode = RandomUtils.nextInt(100, 1000000);
	public static BigDecimal codeQualityIndex;
	public static EnumMap<MeasureEnum, Measure> measures;
    }
}
